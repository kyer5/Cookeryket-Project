package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.dto.menu.MenuDetailIngredientDTO;
import com.example.cookeryket_sb.dto.menu.MenuDetailRecipeDTO;
import com.example.cookeryket_sb.dto.menu.MenuSearchInfoDTO;
import com.example.cookeryket_sb.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    // 메뉴 이름 검색 조회
    @GetMapping("/search")
    public List<MenuSearchInfoDTO> menuSearch(@RequestParam("memberKey") Long memberKey, @RequestParam("menuName") String menuName) {
        List<MenuSearchInfoDTO> menuSearchInfoDTO = menuService.menuSearch(memberKey, menuName);
        return menuSearchInfoDTO;
    }

    // 가격 입력 조회
    @GetMapping("/totalcost/{memberKey}/{memberPrice}")
    public List<MenuSearchInfoDTO> totalCost(@PathVariable("memberKey") Long memberKey, @PathVariable Long memberPrice){
        return menuService.totalCost(memberKey, memberPrice);
    }

    // 메뉴 상세 조회 (재료 리스트)
    @GetMapping("/search/detail/ingredient")
    public MenuDetailIngredientDTO menuDetailIngredient(@RequestParam("memberKey") Long memberKey, @RequestParam("menuName") String menuName) {
        MenuDetailIngredientDTO menuDetailIngredientDTO = menuService.menuDetailIngredient(memberKey, menuName);
        return menuDetailIngredientDTO;
    }

    // 메뉴 상세 조회 (레시피 조회)
    @GetMapping("/search/detail/recipe")
    public MenuDetailRecipeDTO menuDetailRecipe(@RequestParam("memberKey") Long memberKey, @RequestParam("menuName") String menuName) {
        MenuDetailRecipeDTO menuDetailRecipeDTO = menuService.menuDetailRecipe(memberKey, menuName);
        return menuDetailRecipeDTO;
    }

}