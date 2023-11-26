package com.example.cookeryket_sb.service;

import com.example.cookeryket_sb.dto.TotalCostDTO;
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
    private final IngredientRepository ingredientRepository;
    private final MemberRepository memberRepository;


//    public List<MenuIngredientDTO> searchMenu(String menuName) {
//        // MenuRepoditory에서 제공되는 메소드를 사용하여 검색어에 해당하는 메뉴를 찾아온다
//        List<MenuIngredientDTO> menus = menuRepository.findByNameContaining(menuName);
//
//    }


    @Transactional
    public List<TotalCostDTO> totalCost(Long memberKey, Long memberPrice) {
        MemberEntity memberEntity = memberRepository.findById(memberKey)
                .orElseThrow();

        // MemberEntity와 관련된 모든 MyFridgeEntity를 데베에서 찾아 냉장고 재료 변수에 할당한다.
        List<MyFridgeEntity> fridgeIngredients = myfridgeRepository.findByMemberEntity(memberEntity);

        List<IngredientEntity> myIngredientEntity = new ArrayList<>();
        for (MyFridgeEntity fridgeIngredient : fridgeIngredients) {
            // 회원의 냉장에 있는 재료 엔티티
            myIngredientEntity.add(fridgeIngredient.getIngredientEntity());
        }


        List<MenuEntity> menuEntityList = menuRepository.findAll();
        List<List<IngredientEntity>> menuIngredientEntity = new ArrayList<>();

        for (MenuEntity menuEntity : menuEntityList) {
            menuIngredientEntity.add(menuEntity.getIngredientEntityList());
        }

        for (List<IngredientEntity> ingredientEntities : menuIngredientEntity) {
            // 중복된 재료 엔티티 제외
            ingredientEntities.removeIf(myIngredientEntity::contains);

        }

        int totalPrice=0;
        List<TotalCostDTO> priceDTOList = new ArrayList<>();
            // 각 메뉴에 필요한 재료의 가격을 추출

            for (int i = 0; i < menuEntityList.size() ; i++) {
                List<IngredientEntity> menuIngredients = menuIngredientEntity.get(i);

                for (IngredientEntity ingredientEntity : menuIngredients) {
                    totalPrice += ingredientEntity.getIngredientPrice();
                }

                if (totalPrice < memberPrice) {
                    TotalCostDTO priceDTO = new TotalCostDTO();
                    priceDTO.setMenuName(menuEntityList.get(i).getMenuName());

                    priceDTO.setTotalPrice(totalPrice);

                    priceDTOList.add(priceDTO);
                }
                totalPrice = 0;
            }

        return priceDTOList;
    }
}
