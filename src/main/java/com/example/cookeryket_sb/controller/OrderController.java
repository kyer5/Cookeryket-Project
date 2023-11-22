package com.example.cookeryket_sb.controller;

import com.example.cookeryket_sb.dto.CreateOrderDTO;
import com.example.cookeryket_sb.dto.OrderDTO;
import com.example.cookeryket_sb.entity.OrderDetailEntity;
import com.example.cookeryket_sb.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestParam Long memberId, @RequestBody List<CreateOrderDTO> orderList) {
        orderService.placeOrder(memberId, orderList);
        return ResponseEntity.ok("주문이 성공적으로 완료되었습니다.");
    }

    // 주문 조회
    @GetMapping("/history/{memberId}")
    public ResponseEntity<List<OrderDTO>> getOrderHistory(@PathVariable Long memberId) {
        List<OrderDTO> orderHistory = orderService.getOrderHistory(memberId);
        return ResponseEntity.ok(orderHistory);
    }
}