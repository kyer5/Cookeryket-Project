package com.example.cookeryket_sb.service;

import com.example.cookeryket_sb.dto.menu.MenuDetailIngredientDTO;
import com.example.cookeryket_sb.dto.menu.MenuDetailRecipeDTO;
import com.example.cookeryket_sb.dto.menu.MenuSearchInfoDTO;
import com.example.cookeryket_sb.entity.*;
import com.example.cookeryket_sb.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final MyFridgeRepository myFridgeRepository;
    private final MemberRepository memberRepository;

    // 메뉴 검색
    @Transactional
    public List<MenuSearchInfoDTO> menuSearch(Long memberKey, String menuName) {
        MemberEntity memberEntity = memberRepository.findById(memberKey)
                .orElseThrow();

        // DB에 있는 menuName 가져와서 List로 만들기 ..
        List<MenuEntity> searchMenuEntityList = menuRepository.searchByMenuName(menuName);
        List<List<IngredientEntity>> menusRequiredIngredients = extractMenuIngredients(searchMenuEntityList);
        List<IngredientEntity> memberIngredients = getMemberIngredients(memberEntity);

        List<MenuSearchInfoDTO> menuSearchInfoDTOList = new ArrayList<>();
        // 각 메뉴에 필요한 재료의 가격을 추출
        for (int i = 0; i < searchMenuEntityList.size(); i++) {
            List<IngredientEntity> menuIngredients = menusRequiredIngredients.get(i);
            int totalPrice = getTotalPrice(menuIngredients, memberIngredients);

            MenuSearchInfoDTO menuSearchInfoDTO = new MenuSearchInfoDTO();
            menuSearchInfoDTO.setMenuName(searchMenuEntityList.get(i).getMenuName());
            menuSearchInfoDTO.setTotalPrice(totalPrice);
            menuSearchInfoDTO.setMenuImage(searchMenuEntityList.get(i).getMenuImage());
            menuSearchInfoDTOList.add(menuSearchInfoDTO);
        }

        return menuSearchInfoDTOList;
    }

    // 가격 입력 조회
    @Transactional
    public List<MenuSearchInfoDTO> totalCost(Long memberKey, Long memberPrice) {
        MemberEntity memberEntity = memberRepository.findById(memberKey)
                .orElseThrow();

        // 해당 회원이 가지고 있는 재료들의 Entity -> meIngredientEntityList
        List<IngredientEntity> myIngredientEntityList = getMemberIngredients(memberEntity);

        // 메뉴 싹 다 찾아옴
        List<MenuEntity> menuEntityList = menuRepository.findAll();
        // 한 메뉴를 만드는 데 필요한 재료들에 대한 Entity -> menuIngredientEntity
        List<List<IngredientEntity>> menuIngredientEntity = extractMenuIngredients(menuEntityList);

        // totalPrice, 각 메뉴에 필요한 재료의 가격들 추출된 거 DTOList에 저장
        List<MenuSearchInfoDTO> menuSearchInfoDTOList1 = new ArrayList<>();
        // 각 메뉴에 필요한 재료의 가격을 추출
        for (int i = 0; i < menuEntityList.size(); i++) {
            List<IngredientEntity> menuIngredients = menuIngredientEntity.get(i);
            int totalPrice = getTotalPrice(menuIngredients, myIngredientEntityList);

            MenuSearchInfoDTO menuSearchInfoDTO1 = new MenuSearchInfoDTO();
            menuSearchInfoDTO1.setMenuName(menuEntityList.get(i).getMenuName());
            menuSearchInfoDTO1.setTotalPrice(totalPrice);
            menuSearchInfoDTO1.setMenuImage(menuEntityList.get(i).getMenuImage());
            menuSearchInfoDTOList1.add(menuSearchInfoDTO1);
        }

        List<MenuSearchInfoDTO> menuSearchInfoDTOList = menuSearchInfoDTOList1;
        menuSearchInfoDTOList.removeIf(menuSearchInfoDTO -> menuSearchInfoDTO.getTotalPrice() > memberPrice);

        return menuSearchInfoDTOList;
    }

    // 메뉴 클릭 시 상세 조회 (재료 창)
    // 메뉴 이름, 메뉴 사진 URL, 총 가격, 재료 이름, 재료 가격, 재료 사진 URL
    @Transactional
    public MenuDetailIngredientDTO menuDetailIngredient(Long memberKey, String menuName) {
        // 해당 메뉴의 MenuEntity 가져옴
        MenuEntity menuEntity = menuRepository.findByMenuName(menuName)
                .orElseThrow();

        // 해당 회원의 memberEntity 가져옴
        MemberEntity memberEntity = memberRepository.findById(memberKey)
                .orElseThrow();

        // DB에 있는 menuName 가져와서 List로 만들기 ..
        List<IngredientEntity> ingredientEntityList = menuEntity.getIngredientEntityList();
        List<IngredientEntity> memberIngredients = getMemberIngredients(memberEntity);

        String[] ingredientNames = new String[ingredientEntityList.size()];
        int[] ingredientPrices = new int[ingredientEntityList.size()];
        boolean[] haves = new boolean[ingredientEntityList.size()];
        String[] ingredientImages = new String[ingredientEntityList.size()];

        int totalPrice = 0;

        for (int i = 0; i < ingredientEntityList.size(); i++) {
            ingredientNames[i] = ingredientEntityList.get(i).getIngredientName();
            ingredientPrices[i] = ingredientEntityList.get(i).getIngredientPrice();
            haves[i] = memberIngredients.contains(ingredientEntityList.get(i));
            ingredientImages[i] = ingredientEntityList.get(i).getIngredientImage();

            if (!haves[i]) {
                totalPrice += ingredientPrices[i];
            }
        }

        MenuDetailIngredientDTO menuDetailIngredientDTO = new MenuDetailIngredientDTO();
        menuDetailIngredientDTO.setMenuName(menuEntity.getMenuName());
        menuDetailIngredientDTO.setTotalPrice(totalPrice);
        menuDetailIngredientDTO.setIngredientNames(ingredientNames);
        menuDetailIngredientDTO.setIngredientPrices(ingredientPrices);
        menuDetailIngredientDTO.setHaves(haves);
        menuDetailIngredientDTO.setMenuImage(menuEntity.getMenuImage());
        menuDetailIngredientDTO.setIngredientImage(ingredientImages);

        return menuDetailIngredientDTO;
    }

    // 메뉴 클릭 시 상세 조회 (레시피 창)
    // 메뉴 이름, 총 가격, 레시피
    @Transactional
    public MenuDetailRecipeDTO menuDetailRecipe(Long memberKey, String menuName) {
        // 해당 메뉴의 Entity 가져옴
        MenuEntity menuEntity = menuRepository.findByMenuName(menuName)
                .orElseThrow();

        // 해당 회원의 memberEntity 가져옴
        MemberEntity memberEntity = memberRepository.findById(memberKey)
                .orElseThrow();

        // DB에 있는 menuName 가져와서 List로 만들기 ..
        List<IngredientEntity> ingredientEntityList = menuEntity.getIngredientEntityList();
        List<IngredientEntity> memberIngredients = getMemberIngredients(memberEntity);

        String[] ingredientNames = new String[ingredientEntityList.size()];
        int[] ingredientPrices = new int[ingredientEntityList.size()];
        boolean[] haves = new boolean[ingredientEntityList.size()];
        String menuImage;

        int totalPrice = 0;

        for (int i = 0; i < ingredientEntityList.size(); i++) {
            ingredientNames[i] = ingredientEntityList.get(i).getIngredientName();
            ingredientPrices[i] = ingredientEntityList.get(i).getIngredientPrice();
            haves[i] = memberIngredients.contains(ingredientEntityList.get(i));

            if (!haves[i]) {
                totalPrice += ingredientPrices[i];
            }
        }

        MenuDetailRecipeDTO menuDetailRecipeDTO = new MenuDetailRecipeDTO();
        menuDetailRecipeDTO.setMenuName(menuEntity.getMenuName());
        menuDetailRecipeDTO.setTotalPrice(totalPrice);
        menuDetailRecipeDTO.setMenuRecipe(menuEntity.getMenuRecipe());
        menuDetailRecipeDTO.setMenuImage(menuEntity.getMenuImage());

        return menuDetailRecipeDTO;
    }

    // (3) 마냉 재료랑 메뉴에 필요한 재료랑 중복되면 제거

    // (4) totalPrice랑 .. 각 메뉴에 필요한 재료의 가격 추출 ...
    private static int getTotalPrice(List<IngredientEntity> menuIngredients, List<IngredientEntity> memberIngredients) {
        int totalPrice = 0;
        for (IngredientEntity ingredientEntity : menuIngredients) {
            if (!memberIngredients.contains(ingredientEntity)) {
                totalPrice += ingredientEntity.getIngredientPrice();
            }
        }
        return totalPrice;
    }

    // (1, 2) 한 메뉴 만드는 데 필요한 재료 추출 (매개변수 하나)
    private static List<List<IngredientEntity>> extractMenuIngredients(MenuEntity menuEntity) {
        List<List<IngredientEntity>> menuIngredientEntity = new ArrayList<>();
        menuIngredientEntity.add(menuEntity.getIngredientEntityList());

        return menuIngredientEntity;
    }

    // (1, 2) 한 메뉴 만드는 데 필요한 재료 추출
    private static List<List<IngredientEntity>> extractMenuIngredients(List<MenuEntity> menuEntityList) {
        List<List<IngredientEntity>> menuIngredientEntity = new ArrayList<>();
        for (MenuEntity menuEntity : menuEntityList) {
            menuIngredientEntity.add(menuEntity.getIngredientEntityList());
        }
        return menuIngredientEntity;
    }

    // (1, 2)
    private List<IngredientEntity> getMemberIngredients(MemberEntity memberEntity) {
        List<MyFridgeEntity> fridgeIngredients = myFridgeRepository.findByMemberEntity(memberEntity);
        List<IngredientEntity> myIngredientEntity = new ArrayList<>();
        for (MyFridgeEntity fridgeIngredient : fridgeIngredients) {
            // 회원의 냉장에 있는 재료 엔티티
            myIngredientEntity.add(fridgeIngredient.getIngredientEntity());
        }
        return myIngredientEntity;
    }
}