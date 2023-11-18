//package com.example.cookeryket_sb.controller;
//
//import com.example.cookeryket_sb.entity.MyfridgeEntity;
//import com.example.cookeryket_sb.repository.IngredientRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/myfridge")
//public class MyfridgeController {
//
//    private final Map<Integer, MyfridgeEntity> myfidgeMap;
//    private final IngredientRepository ingredientRepository;
//
//    /*
//    @PostConstruct
//    public void init(){
//        myfidgeMap=new HashMap<Integer, MyfridgeEntity>();
//        myfidgeMap.put(1, new MyfridgeEntity(1, 1));
//        myfidgeMap.put(2, new MyfridgeEntity(1, 3));
//        myfidgeMap.put(3, new MyfridgeEntity(1, 5));
//        myfidgeMap.put(4, new MyfridgeEntity(2, 3));
//        myfidgeMap.put(5, new MyfridgeEntity(2, 5));
//    }
//    */
//
//    @Autowired
//    public MyfridgeController(IngredientRepository ingredientRepository){
//        this.ingredientRepository=ingredientRepository;
//        this.myfidgeMap=new HashMap<>();
//    }
//
//    @GetMapping("/{number}")
//    public MyfridgeEntity getMyfridgeEntity(@PathVariable("number") String number){
//        return myfidgeMap.get(number);
//    }
//
//}