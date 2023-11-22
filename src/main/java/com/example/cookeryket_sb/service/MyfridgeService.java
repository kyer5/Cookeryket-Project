package com.example.cookeryket_sb.service;

import com.example.cookeryket_sb.dto.MyfridgeDTO;
import com.example.cookeryket_sb.dto.MyfridgeListDTO;
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

@Service
@RequiredArgsConstructor
public class MyfridgeService {

    private final MyfridgeRepository myfridgeRepository;
    private final MemberRepository memberRepository;
    private final IngredientRepository ingredientRepository;

    private final MemberService memberService;

    /*@Transactional  // 메소드 내의 모든 데이터베이스 작업이 하나의 트랜잭션으로 묶인다
    public List<IngredientEntity> getMyfridgeList(Long memberNumber) {  // 회원 번호를 Controller에서 받아서 해당 회원의 냉장고에 있는 모든 재료를 가져오는 메소드
        MemberEntity memberEntity = memberRepository.findById(memberNumber)  // 회원 번호에 해당하는 회원 정보 조회
                .orElseThrow(IllegalArgumentException::new);  // 회원 정보가 없을 경우 IllegalArgumentException을 발생시킴
        List<MyfridgeEntity> myfridgeEntityList = memberEntity.getMyfridges();  // 해당 회원의 냉장고에 있는 모든 재료를 가져옴

        List<IngredientEntity> ingredientEntityList = new ArrayList<>();  // 회원의 냉장고에 있는 재료를 저장할 리스트를 생성
        for (int i = 0; i < myfridgeEntityList.size(); i++) {  // 냉장고에 있는 모든 재료를 순회
            MyfridgeEntity myfridgeEntity = myfridgeEntityList.get(i);  // i번째 재료를 가져옴

            IngredientEntity ingredientEntity = myfridgeEntity.getIngredientEntity();  // i번째 재료 정보를 가져옴
            ingredientEntityList.add(ingredientEntity);  // 재료 정보를 리스트에 추가
        }

        return ingredientEntityList;  // 회원의 냉장고에 있는 모든 재료 정보가 담긴 리스트를 반환
    }*/

    // My 냉장고에 있는 모든 재료 조회
    @Transactional  // 메소드 내의 모든 데이터베이스 작업이 하나의 트랜잭션으로 묶인다
    public List<MyfridgeListDTO> getMyfridgeList(Long memberNumber) {  // 회원 번호를 Controller에서 받아서 해당 회원의 냉장고에 있는 모든 재료를 가져오는 메소드
        MemberEntity memberEntity = memberRepository.findById(memberNumber)  // 회원 번호에 해당하는 회원 정보 조회
                .orElseThrow(IllegalArgumentException::new);  // 회원 정보가 없을 경우 IllegalArgumentException을 발생시킴
        List<MyfridgeEntity> myfridgeEntityList = memberEntity.getMyfridges();  // 해당 회원의 냉장고에 있는 모든 재료를 가져옴

        List<MyfridgeListDTO> myfridgeList = new ArrayList<>();
        for (int i = 0; i < myfridgeEntityList.size(); i++) {  // 냉장고에 있는 모든 재료를 순회
            MyfridgeEntity myfridgeEntity = myfridgeEntityList.get(i);  // i번째 재료를 가져옴

            IngredientEntity ingredientEntity = myfridgeEntity.getIngredientEntity();  // i번째 재료 정보를 가져옴
            MyfridgeListDTO myfridgeListDTO = new MyfridgeListDTO(
                    myfridgeEntity.getMyfridgeNumber(), ingredientEntity.getIngredientName()
            );
            myfridgeList.add(myfridgeListDTO);
        }
        return myfridgeList;
    }


    // My 냉장고에 재료 추가
    @Transactional  // 메소드 내의 모든 데이터베이스 작업이 하나의 트랜잭션으로 묶인다
    // 회원 번호와 재료 번호를 Controller에서 받아서 해당 회원의 냉장고에 재료를 추가하는 메소드
    public void addMyfridge(MyfridgeDTO myfridgeDTO) {

        MemberEntity memberEntity = memberRepository.findById(myfridgeDTO.getMemberNumber())  // 회원 번호에 해당하는 회원 정보 조회
                        .orElseThrow(IllegalArgumentException::new);  // 회원 정보가 없을 경우 IllegalArgumentException을 발생시킴

        IngredientEntity ingredientEntity = ingredientRepository.findById(myfridgeDTO.getIngredientNumber())  // 재료 번호에 해당하는 재료 정보 조회
                .orElseThrow(IllegalArgumentException::new);  // 재료 번호가 없을(잘못된 인수 값에 메소드에 전달 된) 경우 에러 발생

        MyfridgeEntity newMyfridgeEntity = new MyfridgeEntity();  // 새로운 MyfridgeDTO 객체 생성
        newMyfridgeEntity.setMemberEntity(memberEntity);  // MyfridgeEntity에 회원 정보 설정
        newMyfridgeEntity.setIngredientEntity(ingredientEntity);  // MyfridgeEntity에 재료 정보 설정

        myfridgeRepository.save(newMyfridgeEntity);  // 냉장고에 재료 정보를 저장
    }



    // My 냉장고에서 재료 제거
    @Transactional
    public void deleteMyfridge(Long myfidgeNumber) {  // 냉장고 번호 받아옴

        myfridgeRepository.deleteById(myfidgeNumber);  // 냉장고 번호로 회원의 냉장고 재료 삭제

    }

}
