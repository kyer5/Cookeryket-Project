package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.entity.MenuEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private Map<String, MenuEntity> MenuMap;

    @PostConstruct
    public void init(){
        MenuMap = new HashMap<String, MenuEntity>();
        MenuMap.put("1", new MenuEntity(1,"된장찌개","된장과 양파, 두부를 넣고 물을 끓인다."));
        MenuMap.put("2", new MenuEntity(2,"삼겹살 볶음밥","삼겹살과 마늘, 양파를 넣고 볶는다."));
        MenuMap.put("3", new MenuEntity(3,"계란찜","계란과 물을 넣고 전자레인지를 돌린다."));
        MenuMap.put("4", new MenuEntity(4,"마파두부","고추장과 두부를 넣고 비빈다."));
    }

    @GetMapping("/{number}")
    public MenuEntity getMenuEntity(@PathVariable("number") String number){
        return MenuMap.get(number);
    }
}
