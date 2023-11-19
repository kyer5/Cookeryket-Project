package com.example.cookeryket_sb.service;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientEntity addIngredient(String ingredientNumber){
        // 재료명이 이미 존재하는지 확인
        if(ingredientRepository.findByIngredientName(ingredientNumber)!=null){
            throw new IllegalStateException("이미 존재하는 재료명입니다.");
        }

        IngredientEntity ingredient = new IngredientEntity();

        ingredient.setIngredientName(ingredientNumber);

        return ingredientRepository.save(ingredient);
    }

}
