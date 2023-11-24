package com.example.cookeryket_sb.repository;

import com.example.cookeryket_sb.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository <MenuEntity, Long>{

}