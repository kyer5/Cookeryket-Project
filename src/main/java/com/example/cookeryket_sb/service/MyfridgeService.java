//package com.example.cookeryket_sb.service;
//
//import com.example.cookeryket_sb.entity.IngredientEntity;
//import com.example.cookeryket_sb.entity.MemberEntity;
//import com.example.cookeryket_sb.entity.MyfridgeEntity;
//import com.example.cookeryket_sb.repository.MyfridgeRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class MyfridgeService {
//
//    private final MyfridgeRepository myfridgeRepository;
//
//    public MyfridgeEntity addToMyfridge(MemberEntity member, IngredientEntity ingredientEntity){
//        MyfridgeEntity myfridgeEntity =new MyfridgeEntity();
//        myfridgeEntity.setMemberNumber(member);
//        myfridgeEntity.setIngredientNumber(ingredientEntity);
//
//        return myfridgeRepository.save(myfridgeEntity);
//    }
//
//    public void removeMyfridge(Long myfridgeId){
//        myfridgeRepository.deleteById(myfridgeId);
//    }
//
//    public List<MyfridgeEntity> getMyfridgeContents(Long memberId){
//        return myfridgeRepository.findByMemberId(memberId);
//    }
//}
