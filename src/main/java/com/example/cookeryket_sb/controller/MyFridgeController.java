package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.dto.ingredient.IngredientSearchDTO;
import com.example.cookeryket_sb.dto.myfridge.MyFridgeAddDTO;
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

    // MyFriendgeService를 주입받음
    private final MyFridgeService myFridgeService;


    // 마냉 재료 추가할 때 재료 검색
    @GetMapping("/ingredientSearch")
    public List<IngredientSearchDTO> ingredientSearch(@RequestParam("ingredientName") String ingredientName) {
        List<IngredientSearchDTO> ingredientSearchDTO = myFridgeService.ingredientSearch(ingredientName);
        return ingredientSearchDTO;
    }


    // 회원이 가지고 있는 my냉장고의 재료 리스트를 반환 (HTTP GET 요청을 처리하는 메소드)
    @GetMapping("/list/{memberKey}")
    // list/{memberKey} 형태의 URL로 요청이 들어오면 이 메소드가 호출됨. 여기서 {memberKey}는 URL의 일부로 전달되는 회원 번호
    // @PathVariable 어노테이션을 사용해 URL에서 회원 번호를 추출한다. 추출한 회원 번호는 Long 타입의 memberKey 매개변수로 전달됨
    public List<MyFridgeListDTO> getMyfridgeList(@PathVariable("memberKey") Long memberKey) {
        // 요청받은 회원 번호를 가진 회원의 냉장고에 있는 모든 재료를 조회하여 리스트 형태로 반환. 냉장고 서비스의 getMy냉장고리스트 메소드를 사용
        return myFridgeService.getMyFridgeList(memberKey);
    }


    // 회원의 my냉장고에 재료 추가 (스윙에서 재료 버튼 클릭 시)
    // memberKey와 ingredientKey를 받아서 MyFridge에 추가
    @PostMapping("/add")
    public void addMyFridge(@RequestParam("memberKey") Long memberKey, @RequestBody List<MyFridgeAddDTO> myFridgeAddDTOList) {
        myFridgeService.addMyFridge(memberKey, myFridgeAddDTOList);
    }


    // 회원의 my냉장고에 있는 재료 삭제 (스윙에서 재료명 클릭 후 휴지통 아이콘 눌러서 삭제)
    @DeleteMapping("/delete/{myFridgeKey}")
    public void deleteMyFridge(@PathVariable("myFridgeKey") Long myFridgeKey) {
        myFridgeService.deleteMyFridge(myFridgeKey);
    }

}