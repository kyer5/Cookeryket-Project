package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
    IngredientEntity findByIngredientName(String ingredientName);

    @Query("SELECT me FROM IngredientEntity me WHERE me.ingredientName LIKE %:ingredientName%")
    List<IngredientEntity> searchByIngredientName(@Param("ingredientName") String ingredientName);

}
