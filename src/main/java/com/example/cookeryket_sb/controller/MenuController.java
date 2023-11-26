package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.dto.TotalCostDTO;
import com.example.cookeryket_sb.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

//    @GetMapping("/serch")  // 메뉴 검색 기능
//    public List<MenuIngredientDTO> searchMenu(@RequestParam("menuName") String menuName){
//        return menuService.searchMenu(menuName);
//    }


    @GetMapping("/totalcost/{memberKey}/{memberPrice}")
    public List<TotalCostDTO> totalCost(@PathVariable("memberKey") Long memberKey, @PathVariable Long memberPrice){
        return menuService.totalCost(memberKey, memberPrice);

    }


}
