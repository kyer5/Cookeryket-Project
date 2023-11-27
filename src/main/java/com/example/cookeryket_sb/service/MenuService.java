package com.example.cookeryket_sb.service;

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

    private final MyfridgeRepository myfridgeRepository;
    private final MenuRepository menuRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public List<MenuSearchInfoDTO> menuSearch(Long memberKey, String menuName) {
        // DB에 있는 menuNameList에 사용자가 입력한 메뉴 이름이 contain 되어 있으면 ..
        MemberEntity memberEntity = memberRepository.findById(memberKey)
                .orElseThrow();

        // 먼저 입력한 메뉴가 있는지 확인하기
        // DB에 있는 menuName 가져와서 List로 만들기 ..
        List<MenuEntity> searchMenuEntityList = menuRepository.searchByMenuName(menuName);
        List<List<IngredientEntity>> menusRequiredIngredients = extractMenuIngredients(searchMenuEntityList);
        List<IngredientEntity> memberIngredients = getMemberIngredients(memberEntity);
        for (List<IngredientEntity> ingredientEntities : menusRequiredIngredients) {
            // 중복된 재료 엔티티 제외
            removeIfContains(ingredientEntities, memberIngredients);
        }

        List<MenuSearchInfoDTO> menuSearchInfoDTOList = convertToMenuSearchInfoDTO(searchMenuEntityList, menusRequiredIngredients);
        return menuSearchInfoDTOList;
    }

    // 가격 입력 조회
    @Transactional
    public List<MenuSearchInfoDTO> totalCost(Long memberKey, Long memberPrice) {
        MemberEntity memberEntity = memberRepository.findById(memberKey)
                .orElseThrow();

        // MemberEntity와 관련된 모든 MyfridgeEntity를 DB에서 찾아 냉장고 재료 변수에 할당한다.
        List<IngredientEntity> myIngredientEntityList = getMemberIngredients(memberEntity);

        List<MenuEntity> menuEntityList = menuRepository.findAll();
        List<List<IngredientEntity>> menuIngredientEntity = extractMenuIngredients(menuEntityList);
        for (List<IngredientEntity> ingredientEntities : menuIngredientEntity) {
            // 중복된 재료 엔티티 제외
            removeIfContains(ingredientEntities, myIngredientEntityList);
        }

        List<MenuSearchInfoDTO> menuSearchInfoDTOList = convertToMenuSearchInfoDTO(menuEntityList, menuIngredientEntity);
        for (MenuSearchInfoDTO menuSearchInfoDTO : menuSearchInfoDTOList) {
            if (menuSearchInfoDTO.getTotalPrice() > memberPrice) {
                menuSearchInfoDTOList.remove(menuSearchInfoDTO);
            }
        }

        return menuSearchInfoDTOList;
    }

    private static void removeIfContains(List<IngredientEntity> ingredientEntities, List<IngredientEntity> myIngredientEntityList) {
        for (IngredientEntity ingredientEntity : ingredientEntities) {
            if (myIngredientEntityList.contains(ingredientEntity)) {
                ingredientEntities.remove(ingredientEntity);
            }
        }
    }

    private static List<MenuSearchInfoDTO> convertToMenuSearchInfoDTO(List<MenuEntity> menuEntityList, List<List<IngredientEntity>> menuIngredientEntity) {
        List<MenuSearchInfoDTO> menuSearchInfoDTOList = new ArrayList<>();
        // 각 메뉴에 필요한 재료의 가격을 추출
        for (int i = 0; i < menuEntityList.size(); i++) {
            int totalPrice = 0;
            List<IngredientEntity> menuIngredients = menuIngredientEntity.get(i);
            for (IngredientEntity ingredientEntity : menuIngredients) {
                totalPrice += ingredientEntity.getIngredientPrice();
            }

            MenuSearchInfoDTO menuSearchInfoDTO = new MenuSearchInfoDTO();
            menuSearchInfoDTO.setMenuName(menuEntityList.get(i).getMenuName());
            menuSearchInfoDTO.setTotalPrice(totalPrice);
            menuSearchInfoDTOList.add(menuSearchInfoDTO);
        }
        return menuSearchInfoDTOList;
    }

    private static List<List<IngredientEntity>> extractMenuIngredients(List<MenuEntity> menuEntityList) {
        List<List<IngredientEntity>> menuIngredientEntity = new ArrayList<>();
        for (MenuEntity menuEntity : menuEntityList) {
            menuIngredientEntity.add(menuEntity.getIngredientEntityList());
        }
        return menuIngredientEntity;
    }

    private List<IngredientEntity> getMemberIngredients(MemberEntity memberEntity) {
        List<MyFridgeEntity> fridgeIngredients = myfridgeRepository.findByMemberEntity(memberEntity);
        List<IngredientEntity> myIngredientEntity = new ArrayList<>();
        for (MyFridgeEntity fridgeIngredient : fridgeIngredients) {
            // 회원의 냉장에 있는 재료 엔티티
            myIngredientEntity.add(fridgeIngredient.getIngredientEntity());
        }
        return myIngredientEntity;
    }
}
