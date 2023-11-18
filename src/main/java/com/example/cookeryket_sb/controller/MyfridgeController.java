package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.MyfridgeEntity;
import com.example.cookeryket_sb.service.IngredientService;
import com.example.cookeryket_sb.service.MyfridgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myfridge")
@Slf4j
@RequiredArgsConstructor
public class MyfridgeController {

    // MyfriendgeService를 주입받음
    private final MyfridgeService myfridgeService;

    // 회원이 가지고 있는 my냉장고의 재료 리스트를 반환
    @GetMapping("/list/{id}")
    public List<MyfridgeEntity> getMyfridgeList(@PathVariable("id") Long memberNumber) {
        return myfridgeService.getMyfridgeList(memberNumber);
    }

    // 회원의 my냉장고에 재료 추가 (스윙에서 재료 버튼 클릭 시)
//    @PostMapping("/add/{id}")
//    public void addMyfridge(@RequestBody IngredientEntity ingredient){
//        myfridgeService.addMyfridge(ingredient);
//    }

    // memberNumber와 ingredientNumber를 받아서 Myfridge에 추가
    @PostMapping("/add/{id}")
    public void addMyfridge(@RequestParam Long memberNumber, @RequestParam int ingredientNumber) {
        myfridgeService.addMyfridge(memberNumber, ingredientNumber);
    }

    // 회원의 my냉장고에 있는 재료 삭제 (스윙에서 재료명 클릭 후 휴지통 아이콘 눌러서 삭제)
//    @DeleteMapping("/delete/{id}")
//    public void deleteMyfridge(@RequestParam("id") @RequestBody IngredientEntity ingredient){
//        MyfridgeService.deleteMyfridge(ingredient);
//    }

    @PostMapping("/delete/{id}")
    public void deleteMyfridge(@RequestParam Long memberNumber, @RequestParam int ingredientNumber) {
        myfridgeService.deleteMyfridge(memberNumber, ingredientNumber);
    }
}