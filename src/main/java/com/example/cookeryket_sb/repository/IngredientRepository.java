package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
    IngredientEntity findByIngredientName(String ingredientName);

}
