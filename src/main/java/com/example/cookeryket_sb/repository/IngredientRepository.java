package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MyfridgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
    IngredientEntity findByIngredientName(String ingredientName);

}
