package com.example.cookeryket_sb.service;

import com.example.cookeryket_sb.dto.CreateOrderDTO;
import com.example.cookeryket_sb.dto.OrderDTO;
import com.example.cookeryket_sb.dto.OrderDetailDTO;
import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.OrderDetailEntity;
import com.example.cookeryket_sb.entity.OrderEntity;
import com.example.cookeryket_sb.repository.IngredientRepository;
import com.example.cookeryket_sb.repository.MemberRepository;
import com.example.cookeryket_sb.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final IngredientRepository ingredientRepository;

    @Transactional
    public OrderEntity placeOrder(Long memberNumber, List<CreateOrderDTO> orderList) {
        MemberEntity memberEntity = memberRepository.findById(memberNumber)
                .orElseThrow();

        OrderEntity order = new OrderEntity();
        List<OrderDetailEntity> orderDetails = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            CreateOrderDTO createOrderDTO = orderList.get(i);
            Long ingredientNumber = createOrderDTO.getIngredientNumber();
            IngredientEntity ingredientEntity = ingredientRepository.findById(ingredientNumber)
                    .orElseThrow();

            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setIngredient(ingredientEntity);
            orderDetailEntity.setOrderDetailQuantity(createOrderDTO.getOrderQuantity());
            orderDetailEntity.setOrder(order);

            orderDetails.add(orderDetailEntity);
        }

        order.setMember(memberEntity);
        order.setOrderDetails(orderDetails);
        int totalPrice = calculateTotalPrice(orderDetails);
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    private int calculateTotalPrice(List<OrderDetailEntity> orderDetails) {
        int totalPrice = 0;
        for (OrderDetailEntity orderDetail : orderDetails) {
            IngredientEntity ingredient = orderDetail.getIngredient();
            totalPrice += orderDetail.getOrderDetailQuantity() * ingredient.getIngredientPrice();
        }
        return totalPrice;
    }

    public List<OrderDTO> getOrderHistory(Long memberId) {
        // 해당 회원의 주문 내역을 조회
        List<OrderEntity> orders = orderRepository.findByMemberMemberNumber(memberId);

        // OrderDTO로 변환하여 반환
        return orders.stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO convertToOrderDTO(OrderEntity order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderNumber(order.getOrderNumber());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setTotalPrice(order.getTotalPrice());

        // 상세 주문 정보 변환 및 설정
        List<OrderDetailDTO> orderDetailDTOs = order.getOrderDetails().stream()
                .map(this::convertToOrderDetailDTO)
                .collect(Collectors.toList());

        orderDTO.setOrderDetails(orderDetailDTOs);

        return orderDTO;
    }

    private OrderDetailDTO convertToOrderDetailDTO(OrderDetailEntity orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setIngredientName(orderDetail.getIngredient().getIngredientName());
        orderDetailDTO.setIngredientPrice(orderDetail.getIngredient().getIngredientPrice());
        orderDetailDTO.setOrderDetailCount(orderDetail.getOrderDetailQuantity());

        return orderDetailDTO;
    }
}