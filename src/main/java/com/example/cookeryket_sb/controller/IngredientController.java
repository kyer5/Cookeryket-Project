package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.entity.IngredientEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private Map<String, IngredientEntity> ingredientMap;

    @PostConstruct
    public void init(){
        ingredientMap=new HashMap<String, IngredientEntity>();
        ingredientMap.put("1", new IngredientEntity(1, "소금", 100, 111, "국내산 소금"));
        ingredientMap.put("2", new IngredientEntity(2, "된장", 800, 22, "국내산 된장"));
        ingredientMap.put("3", new IngredientEntity(3, "삼겹살", 9900, 301, "미국산 삼겹살"));
        ingredientMap.put("4", new IngredientEntity(4, "두부", 300, 42, "국내산 두부"));
        ingredientMap.put("5", new IngredientEntity(5, "고추장", 1000, 13, "국내산 고추장"));
        ingredientMap.put("6", new IngredientEntity(6, "양파", 1200, 58, "국내산 양파"));
        ingredientMap.put("7", new IngredientEntity(7, "마늘", 600, 49, "국내산 마늘"));

    }

    @GetMapping("/{number}")
    public IngredientEntity getIngredientEntity(@PathVariable("number") String number){
        return ingredientMap.get(number);
    }


}
