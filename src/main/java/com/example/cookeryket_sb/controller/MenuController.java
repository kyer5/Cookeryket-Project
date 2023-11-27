package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.dto.menu.MenuSearchInfoDTO;
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


    // 메뉴 검색 조회
    @GetMapping("/search")
    public List<MenuSearchInfoDTO> menuSearch(@RequestParam("memberKey") Long memberKey, @RequestParam("menuName") String menuName) {
        List<MenuSearchInfoDTO> menuSearchInfoDTO = menuService.menuSearch(memberKey, menuName);
        return menuSearchInfoDTO;
    }


    // 가격 입력
    @GetMapping("/totalcost/{memberKey}/{memberPrice}")
    public List<MenuSearchInfoDTO> totalCost(@PathVariable("memberKey") Long memberKey, @PathVariable Long memberPrice){
        return menuService.totalCost(memberKey, memberPrice);

    }
}
