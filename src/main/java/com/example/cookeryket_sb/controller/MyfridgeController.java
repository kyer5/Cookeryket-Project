package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.MyfridgeEntity;
import com.example.cookeryket_sb.service.MyfridgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @GetMapping("/list/{memberNumber}")
    public List<IngredientEntity> getMyfridgeList(@PathVariable("memberNumber") Long memberNumber) {
        return myfridgeService.getMyfridgeList(memberNumber);
    }

    // 회원의 my냉장고에 재료 추가 (스윙에서 재료 버튼 클릭 시)
//    @PostMapping("/add/{id}")
//    public void addMyfridge(@RequestBody IngredientEntity ingredient){
//        myfridgeService.addMyfridge(ingredient);
//    }

    // memberNumber와 ingredientNumber를 받아서 Myfridge에 추가
    @PostMapping("/add/{memberNumber}/{ingredientNumber}")
    public void addMyfridge(@PathVariable("memberNumber") Long memberNumber, @PathVariable("ingredientNumber") Long ingredientNumber) {
        myfridgeService.addMyfridge(memberNumber, ingredientNumber);
    }

    // 회원의 my냉장고에 있는 재료 삭제 (스윙에서 재료명 클릭 후 휴지통 아이콘 눌러서 삭제)
//    @DeleteMapping("/delete/{id}")
//    public void deleteMyfridge(@RequestParam("id") @RequestBody IngredientEntity ingredient){
//        MyfridgeService.deleteMyfridge(ingredient);
//    }

    @DeleteMapping("/delete/{memberNumber}/{ingredientNumber}")
    public void deleteMyfridge(@PathVariable("memberNumber") Long memberNumber, @PathVariable("ingredientNumber") Long ingredientNumber) {
        myfridgeService.deleteMyfridge(memberNumber, ingredientNumber);
    }

//    @GetMapping("/{memberId}")
//    public List<IngredientEntity> getAllIngredients(@PathVariable Long memberId) {
//        return myFridgeService.getAllIngredients(memberId);
//    }
//
//    @PostMapping("/{memberId}")
//    public MyfridgeEntity addIngredient(@PathVariable Long memberId, @RequestBody IngredientEntity ingredient) {
//        return myFridgeService.addIngredient(memberId, ingredient);
//    }
//
//    @DeleteMapping("/{memberId}/{ingredientId}")
//    public String deleteIngredient(@PathVariable Long memberId, @PathVariable Long ingredientId) {
//        return myFridgeService.deleteIngredient(memberId, ingredientId);
//    }
}