package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.dto.basket.BasketAddDTO;
import com.example.cookeryket_sb.dto.basket.BasketInquiryDTO;
import com.example.cookeryket_sb.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    // 장바구니에 상품 추가 (동일 재료는 수량만 카운트 됨)
    @PostMapping("/add")
    public void addBasket(@RequestParam("memberKey") Long memberKey, @RequestBody List<BasketAddDTO> basketDTOList) {
        basketService.addBasket(memberKey, basketDTOList);
    }

    // 장바구니에 상품 삭제
    @DeleteMapping("/delete")
    public void deleteBasket(@RequestParam("basketKey") Long basketKey) {
        basketService.deleteBasket(basketKey);
    }

    // 장바구니에 담긴 상품 수량 수정
    @PatchMapping("/update")
    public void updateBasket(@RequestParam("basketKey") Long basketKey, @RequestParam("basketQuantity") int basketQuantity) {
        basketService.updateBasket(basketKey, basketQuantity);
    }

    // 장바구니 조회
    @GetMapping("/inquiry")
    public List<BasketInquiryDTO> inquiryBasket(@RequestParam("memberKey") Long memberKey) {
        List<BasketInquiryDTO> basketInquiryDTOList = basketService.inquiryBasket(memberKey);
        return basketInquiryDTOList;
    }
}