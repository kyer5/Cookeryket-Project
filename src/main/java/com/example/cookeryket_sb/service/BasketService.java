package com.example.cookeryket_sb.service;

import com.example.cookeryket_sb.dto.basket.BasketAddDTO;
import com.example.cookeryket_sb.dto.basket.BasketInquiryDTO;
import com.example.cookeryket_sb.entity.BasketEntity;
import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.repository.BasketRepository;
import com.example.cookeryket_sb.repository.IngredientRepository;
import com.example.cookeryket_sb.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final MemberRepository memberRepository;
    private final IngredientRepository ingredientRepository;

    // 장바구니에 상품 추가
    // 같은 재료 담으면 증가되게 !!!
    @Transactional
    public void addBasket(Long memberKey, List<BasketAddDTO> basketList) {
        // memberKey로 memberEntity 찾기
        MemberEntity memberEntity = memberRepository.findById(memberKey)
                .orElseThrow();

        for (int i = 0; i < basketList.size(); i++) {
            // ??
            BasketAddDTO basketAddDTO = basketList.get(i);
            // 장바구니에 담은 재료 번호 가져옴
            Long ingredientKey = basketAddDTO.getIngredientKey();
            // 위 해당 재료 번호의 IngredientEntity 검색
            IngredientEntity ingredientEntity = ingredientRepository.findById(ingredientKey)
                    .orElseThrow();

            BasketEntity basketEntity = getBasketEntity(memberEntity, ingredientEntity, basketAddDTO);

            // 장바구니 엔티티를 DB에 저장
            basketRepository.save(basketEntity);
        }

    }

    /* 한 회원에 해당하는 장바구니 사이즈만큼 for문 돌리기
     * 장바구니에 담겨있는 재료 번호랑 새롭게 추가하려고 하는 재료 번호랑 같은지 확인
     * 같으면 원래 담겨있는 장바구니 번호에 해당하는 재료 수량 증가 */
    private BasketEntity getBasketEntity(MemberEntity memberEntity, IngredientEntity ingredientEntity, BasketAddDTO basketAddDTO) {
        List<BasketEntity> basketEntities = basketRepository.findByMemberEntity(memberEntity);
        for (int j = 0; j < basketEntities.size(); j++) {
            Long ingredientKey1 = ingredientEntity.getIngredientKey();
            BasketEntity basketEntity1 = basketEntities.get(j);
            Long ingredientKey2 = basketEntity1.getIngredientEntity().getIngredientKey();

            if (ingredientKey1.equals(ingredientKey2)) {
                basketEntity1.increaseQuantity(basketAddDTO.getBasketQuantity());
                return basketEntity1;
            }
        }

        BasketEntity basketEntity = new BasketEntity();
        // 현재 장바구니 항목의 재료 번호 설정
        basketEntity.setIngredientEntity(ingredientEntity);
        // DTO에서 장바구니에 담은 수량 설정
        basketEntity.setBasketQuantity(basketAddDTO.getBasketQuantity());

        // 해당 장바구니의 회원? 설정
        basketEntity.setMemberEntity(memberEntity);
        return basketEntity;
    }

    // 장바구니 상품 삭제
    @Transactional
    public void deleteBasket(Long basketKey) {
        basketRepository.deleteById(basketKey);
    }

    // 장바구니에 담긴 상품 수량 수정
    @Transactional
    public void updateBasket(Long basketKey, int basketQuantity) {
        // basketKey로 basketEntity 찾기
        BasketEntity basketEntity = basketRepository.findById(basketKey)
                .orElseThrow();
        // 수량 업뎃
        basketEntity.setBasketQuantity(basketQuantity);
        // DB에 업데이트 !!!
        basketRepository.save(basketEntity);
    }

    // 장바구니 조회하기
    @Transactional
    public List<BasketInquiryDTO> inquiryBasket(Long memberKey) {
        // memberKey로 memberEntity 찾기
        MemberEntity memberEntity = memberRepository.findById(memberKey)
                .orElseThrow();

        // 회원의 장바구니 조회
        List<BasketEntity> basketEntities = basketRepository.findByMemberEntity(memberEntity);

        List<BasketInquiryDTO> basketInquiryDTOList = new ArrayList<>();
        // Entity를 DTO로 변환 -> DTO 새로 만들기 !!
        // 재료 이름, 재료 가격, 장바구니에 담긴 수량, 장바구니 번호
        for (BasketEntity basketEntity : basketEntities) {
            IngredientEntity ingredientEntity = basketEntity.getIngredientEntity();
            BasketInquiryDTO basketInquiryDTO = new BasketInquiryDTO();
            basketInquiryDTO.setIngredientName(ingredientEntity.getIngredientName());
            basketInquiryDTO.setIngredientPrice(ingredientEntity.getIngredientPrice());
            basketInquiryDTO.setBasketQuantity(basketEntity.getBasketQuantity());
            basketInquiryDTO.setBasketKey(basketEntity.getBasketKey());

            basketInquiryDTOList.add(basketInquiryDTO);
        }

        return basketInquiryDTOList;
    }

}
