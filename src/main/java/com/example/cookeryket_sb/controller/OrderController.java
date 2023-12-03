package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.dto.order.OrderCreateDTO;
import com.example.cookeryket_sb.dto.order.OrderDetailsDTO;
import com.example.cookeryket_sb.dto.order.OrderInquiryDTO;
import com.example.cookeryket_sb.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문
    @PostMapping("/place")
    public void placeOrder(@RequestParam Long memberKey, @RequestBody List<OrderCreateDTO> orderList) {
        orderService.placeOrder(memberKey, orderList);
    }

    // 주문 조회
    @GetMapping("/inquiry")
    public List<OrderInquiryDTO> inquiryOrder(@RequestParam("memberKey") Long memberKey) {
        List<OrderInquiryDTO> orderInquiryDTOList = orderService.inquiryOrder(memberKey);
        return orderInquiryDTOList;
    }

    // 주문 상세 조회
    @GetMapping("/inquiry/detail")
    public OrderDetailsDTO inquiryDetailOrder(@RequestParam("memberKey") Long memberKey, @RequestParam("orderKey") Long orderKey) {
        OrderDetailsDTO orderDetailsDTO = orderService.inquiryDetailOrder(memberKey, orderKey);
        return orderDetailsDTO;
    }
}