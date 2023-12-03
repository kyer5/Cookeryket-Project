package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.dto.ingredient.IngredientSearchDTO;
import com.example.cookeryket_sb.dto.myfridge.MyFridgeDTO;
import com.example.cookeryket_sb.dto.myfridge.MyFridgeListDTO;
import com.example.cookeryket_sb.service.MyFridgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myfridge")
@Slf4j
@RequiredArgsConstructor
public class MyFridgeController {

    private final MyFridgeService myFridgeService;

    // my 냉장고 재료 검색
    @GetMapping("/ingredientSearch")
    public List<IngredientSearchDTO> ingredientSearch(@RequestParam("ingredientName") String ingredientName) {
        List<IngredientSearchDTO> ingredientSearchDTO = myFridgeService.ingredientSearch(ingredientName);
        return ingredientSearchDTO;
    }

    // my 냉장고 조회
    @GetMapping("/list/{memberKey}")
    public List<MyFridgeListDTO> getMyfridgeList(@PathVariable("memberKey") Long memberKey) {
        return myFridgeService.getMyFridgeList(memberKey);
    }

    // my 냉장고 재료 추가
    @PostMapping("/add/{memberKey}/{ingredientKey}")
    public void addMyfridge(@PathVariable("memberKey") Long memberKey, @PathVariable("ingredientKey") Long ingredientKey) {
        MyFridgeDTO myFridgeDTO = new MyFridgeDTO(memberKey, ingredientKey);
        myFridgeService.addMyFridge(myFridgeDTO);
    }

    // my 냉장고 재료 삭제
    @DeleteMapping("/delete/{myFridgeKey}")
    public void deleteMyfridge(@PathVariable("myFridgeKey") Long myFridgeKey) {
        myFridgeService.deleteMyFridge(myFridgeKey);
    }

}