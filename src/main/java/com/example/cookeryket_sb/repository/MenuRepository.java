package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    @Query("SELECT me FROM MenuEntity me WHERE me.menuName LIKE %:menuName%")
    List<MenuEntity> searchByMenuName(@Param(value = "menuName") String menuName);

    Optional<MenuEntity> findByMenuName(String menuName);
}