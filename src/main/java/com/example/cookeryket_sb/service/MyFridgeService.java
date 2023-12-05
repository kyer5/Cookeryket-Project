package com.example.cookeryket_sb.service;

import com.example.cookeryket_sb.dto.ingredient.IngredientSearchDTO;
import com.example.cookeryket_sb.dto.myfridge.MyFridgeAddDTO;
import com.example.cookeryket_sb.dto.myfridge.MyFridgeDTO;
import com.example.cookeryket_sb.dto.myfridge.MyFridgeListDTO;
import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.MyFridgeEntity;
import com.example.cookeryket_sb.exception.CookeryketException;
import com.example.cookeryket_sb.repository.IngredientRepository;
import com.example.cookeryket_sb.repository.MemberRepository;
import com.example.cookeryket_sb.repository.MyFridgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyFridgeService {

    private final MyFridgeRepository myFridgeRepository;
    private final MemberRepository memberRepository;
    private final IngredientRepository ingredientRepository;


    // My 냉장고 조회
    @Transactional  // 메소드 내의 모든 데이터베이스 작업이 하나의 트랜잭션으로 묶인다
    // 회원 번호를 Controller에서 받아서 해당 회원의 냉장고에 있는 모든 재료를 가져오는 메소드
    public List<MyFridgeListDTO> getMyFridgeList(Long memberKey) {
        MemberEntity memberEntity = memberRepository.findById(memberKey)  // 회원 번호에 해당하는 회원 정보 조회
                .orElseThrow(IllegalArgumentException::new);  // 회원 정보가 없을 경우 오류를 발생시킴
        List<MyFridgeEntity> myFridgeEntityList = memberEntity.getMyFridges();  // 해당 회원의 냉장고에 있는 모든 재료를 가져옴

        List<MyFridgeListDTO> myFridgeList = new ArrayList<>();
        for (int i = 0; i < myFridgeEntityList.size(); i++) {  // 냉장고에 있는 모든 재료를 순회
            MyFridgeEntity myFridgeEntity = myFridgeEntityList.get(i);  // i번째 재료를 가져옴

            IngredientEntity ingredientEntity = myFridgeEntity.getIngredientEntity();  // i번째 재료 정보를 가져옴
            MyFridgeListDTO myFridgeListDTO = new MyFridgeListDTO(
                    myFridgeEntity.getMyFridgeKey(), ingredientEntity.getIngredientName()
            );
            myFridgeList.add(myFridgeListDTO);
        }
        return myFridgeList;
    }

    // My 냉장고에 재료 추가
    @Transactional  // 메소드 내의 모든 데이터베이스 작업이 하나의 트랜잭션으로 묶인다
    // 회원 번호와 재료 번호를 Controller에서 받아서 해당 회원의 냉장고에 재료를 추가하는 메소드
    public void addMyFridge(Long MemberKey, List<MyFridgeAddDTO> myFridgeAddDTOList) {

        MemberEntity memberEntity = memberRepository.findById(MemberKey)  // 회원 번호에 해당하는 회원 정보 조회
                .orElseThrow(IllegalArgumentException::new);  // 회원 정보가 없을 경우 IllegalArgumentException을 발생시킴

        for (MyFridgeAddDTO myFridgeAddDTO : myFridgeAddDTOList) {
            IngredientEntity ingredientEntity = ingredientRepository.findByIngredientName(myFridgeAddDTO.getIngredientName());  // 재료 번호에 해당하는 재료 정보 조회;  // 재료 번호가 없을(잘못된 인수 값에 메소드에 전달 된) 경우 에러 발생

            MyFridgeEntity myFridgeEntity = new MyFridgeEntity();  // 새로운 MyFridgeDTO 객체 생성
            myFridgeEntity.setMemberEntity(memberEntity);  // MyfridgeEntity에 회원 정보 설정
            myFridgeEntity.setIngredientEntity(ingredientEntity);  // MyfridgeEntity에 재료 정보 설정

            myFridgeRepository.save(myFridgeEntity);  // 냉장고에 재료 정보를 저장
        }
    }

    // My 냉장고에서 재료 제거
    @Transactional
    public void deleteMyFridge(Long myFridgeKey) {  // 냉장고 번호 받아옴

        myFridgeRepository.deleteById(myFridgeKey);  // 냉장고 번호로 회원의 냉장고 재료 삭제
    }

    // My 냉장고에서 재료 검색
    @Transactional
    public List<IngredientSearchDTO> ingredientSearch(String ingredientName) {
        List<IngredientEntity> ingredientEntityList = ingredientRepository
                .searchByIngredientName(ingredientName);

        if (ingredientEntityList.isEmpty()) {
            throw new CookeryketException("해당 재료가 존재하지 않습니다.");
        }

        List<IngredientSearchDTO> ingredientSearchDTOList = new ArrayList<>();
        for (IngredientEntity ingredientEntity : ingredientEntityList) {
            IngredientSearchDTO ingredientSearchDTO = new IngredientSearchDTO();
            ingredientSearchDTO.setIngredientKey(ingredientEntity.getIngredientKey());
            ingredientSearchDTO.setIngredientName(ingredientEntity.getIngredientName());

            ingredientSearchDTOList.add(ingredientSearchDTO);
        }
        return ingredientSearchDTOList;
    }
}