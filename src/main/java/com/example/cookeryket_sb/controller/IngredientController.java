package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.service.IngredientService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    // 재료 검색 기능, 재료 조회 기능
    @GetMapping
    public List<IngredientEntity> getAllIngredients(){
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public IngredientEntity getIngredientById(@PathVariable("id") Long id){
        return ingredientService.getIngredientById(id);
    }

    @PostMapping
    public void addIngredient(@RequestBody IngredientEntity ingredient){
        ingredientService.addIngredient(ingredient);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable("id") Long id){
        ingredientService.deleteIngredient(id);
    }


}
