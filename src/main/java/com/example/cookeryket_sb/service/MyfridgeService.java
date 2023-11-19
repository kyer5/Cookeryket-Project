package com.example.cookeryket_sb.service;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.MyfridgeEntity;
import com.example.cookeryket_sb.repository.IngredientRepository;
import com.example.cookeryket_sb.repository.MemberRepository;
import com.example.cookeryket_sb.repository.MyfridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyfridgeService {

    private final MyfridgeRepository myfridgeRepository;
    private final MemberRepository memberRepository;
    private final IngredientRepository ingredientRepository;

    private final MemberService memberService;
    private final IngredientService ingredientService;

    // My 냉장고에 있는 모든 재료 조회
    @Transactional
    public List<IngredientEntity> getMyfridgeList(Long memberNumber) {
        MemberEntity memberEntity = memberRepository.findById(memberNumber)
                .orElseThrow(IllegalArgumentException::new);
        List<MyfridgeEntity> myfridgeEntityList = memberEntity.getMyfridges();

        List<IngredientEntity> ingredientEntityList = new ArrayList<>();
        for (int i = 0; i < myfridgeEntityList.size(); i++) {
            MyfridgeEntity myfridgeEntity = myfridgeEntityList.get(i);

            IngredientEntity ingredientEntity = myfridgeEntity.getIngredientEntity();
            ingredientEntityList.add(ingredientEntity);
        }

        return ingredientEntityList;
    }


    // My 냉장고에 재료 추가
    public MyfridgeEntity addMyfridge(Long memberNumber, Long ingredientNumber) {
        MemberEntity member = new MemberEntity();
        member.setMemberNumber(memberNumber);

        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setIngredientNumber(ingredientNumber);

        MyfridgeEntity newIngredient = new MyfridgeEntity();
        newIngredient.setMemberEntity(member);
        newIngredient.setIngredientEntity(ingredient);


        return myfridgeRepository.save(newIngredient);
    }

    public IngredientEntity addIngredient(String ingredientNumber) {
        // 재료명이 이미 존재하는지 확인
        if (ingredientRepository.findByIngredientName(ingredientNumber) != null) {
            throw new IllegalStateException("이미 존재하는 재료명입니다.");
        }

        IngredientEntity ingredient = new IngredientEntity();

        ingredient.setIngredientName(ingredientNumber);

        return ingredientRepository.save(ingredient);
    }

    // My 냉장고에서 재료 제거
    public void deleteMyfridge(Long memberNumber, Long ingredientNumber) {
        MyfridgeEntity ingredient = myfridgeRepository.findByMemberEntityAndIngredientEntity(null, null);
        myfridgeRepository.delete(ingredient);
    }

}
