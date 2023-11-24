package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

//    @GetMapping("/serch")
//    public List<MenuIngredientDTO> searchMenu(@RequestParam("menuName") String menuName){
//        return menuService.searchMenu(menuName);
//    }


    @GetMapping("/totalcost/{memberNumber}/{memberPrice}")
    public ResponseEntity<String> totalCost(@PathVariable("memberNumber") Long memberNumber, @RequestBody Long memberPrice){
        menuService.totalCost(memberNumber, memberPrice);
        return ResponseEntity.ok("메뉴별 가격 반영함");
    }

}
