package com.example.cookeryket_sb.service;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.MyfridgeEntity;
import com.example.cookeryket_sb.repository.MyfridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyfridgeService {

    private final MyfridgeRepository myfridgeRepository;

    // My 냉장고에 있는 모든 재료 조회
    public List<MyfridgeEntity> getMyfridgeList(Long memberNumber){
        return myfridgeRepository.findByMemberNumber(memberNumber);
    }


//    public void addMyfridge(IngredientEntity ingredientId){
////        MemberEntity member = new MemberEntity();
////        member.setMemberNumber(memberId);
////
////        IngredientEntity ingredient = new IngredientEntity();
////        ingredient.setIngredientNumber(ingredientId);
////
////        MyfridgeEntity myfridgeEntity=new MyfridgeEntity();
////        myfridgeEntity.setMemberNumber(member);
////        myfridgeEntity.setIngredientNumber(ingredient);
//
//        myfridgeRepository.save(ingredientId);
//    }

//    public void deleteMyfridge(IngredientEntity ingredient){
//        myfridgeRepository.deleteMyfridge(ingredient);
//    }


    // My 냉장고에 재료 추가
    public void addMyfridge(Long memberNumber, int ingredientNumber){
        MemberEntity member = new MemberEntity();
        member.setMemberNumber(memberNumber);

        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setIngredientNumber(ingredientNumber);

        MyfridgeEntity myfridgeEntity=new MyfridgeEntity();
        myfridgeEntity.setMemberNumber(member);
        myfridgeEntity.setIngredientNumber(ingredient);

        myfridgeRepository.save(myfridgeEntity);
    }

    // My 냉장고에서 재료 제거
    public void deleteMyfridge(Long memberNumber, int ingredientNumber){
        myfridgeRepository.deleteMyfridge(memberNumber, ingredientNumber);
    }






}
