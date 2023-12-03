package com.example.cookeryket_sb.service;

import com.example.cookeryket_sb.dto.order.OrderCreateDTO;
import com.example.cookeryket_sb.dto.order.OrderDetailsDTO;
import com.example.cookeryket_sb.dto.order.OrderInquiryDTO;
import com.example.cookeryket_sb.entity.IngredientEntity;
import com.example.cookeryket_sb.entity.MemberEntity;
import com.example.cookeryket_sb.entity.OrderDetailEntity;
import com.example.cookeryket_sb.entity.OrderEntity;
import com.example.cookeryket_sb.repository.IngredientRepository;
import com.example.cookeryket_sb.repository.MemberRepository;
import com.example.cookeryket_sb.repository.OrderRepository;
import com.example.cookeryket_sb.exception.CookeryketException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final IngredientRepository ingredientRepository;

    // 상품 주문
    @Transactional
    // 주문하는 회원 식별, CreateOrderDTO 객체가 담긴 orderList 배열 (객체 배열 !!)
    // CreateOrderDTO <= ingredientKey, orderDetailQuantity
    public OrderEntity placeOrder(Long memberKey, List<OrderCreateDTO> orderList) {
        MemberEntity memberEntity = memberRepository.findById(memberKey)
                .orElseThrow();

        OrderEntity orderEntity = new OrderEntity();
        // OrderDetailEntity 객체가 담긴 orderDetails 배열 (객체 배열 !!)
        List<OrderDetailEntity> orderDetails = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            OrderCreateDTO createOrderDTO = orderList.get(i);
            // 현재 주문 항목의 재료 번호를 가져옴
            Long ingredientKey = createOrderDTO.getIngredientKey();
            // 위 해당 번호의 IngredientEntity 검색
            IngredientEntity ingredientEntity = ingredientRepository.findById(ingredientKey)
                    .orElseThrow();

            validateOrderQuantity(createOrderDTO.getOrderQuantity(), ingredientEntity);

            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setIngredientEntity(ingredientEntity);
            orderDetailEntity.setOrderDetailQuantity(createOrderDTO.getOrderQuantity());
            orderDetailEntity.setOrderEntity(orderEntity);

            orderDetails.add(orderDetailEntity);
        }

        orderEntity.setMemberEntity(memberEntity);
        orderEntity.setOrderDate(LocalDate.now().toString());
        orderEntity.setOrderDetails(orderDetails);
        int totalPrice = calculateTotalPrice(orderDetails);
        orderEntity.setTotalPrice(totalPrice);

        updateIngredientQuantity(orderDetails);

        return orderRepository.save(orderEntity);
    }

    // 총 가격 계산
    private int calculateTotalPrice(List<OrderDetailEntity> orderDetails) {
        int totalPrice = 0;
        for (OrderDetailEntity orderDetail : orderDetails) {
            IngredientEntity ingredient = orderDetail.getIngredientEntity();
            totalPrice += orderDetail.getOrderDetailQuantity() * ingredient.getIngredientPrice();
        }
        return totalPrice;
    }

    // 재고 수량 업데이트
    private void updateIngredientQuantity(List<OrderDetailEntity> orderDetails) {
        for (OrderDetailEntity orderDetail : orderDetails) {
            IngredientEntity ingredient = orderDetail.getIngredientEntity();
            // 현재 재고 수량 - 주문 수량
            int newQuantity = ingredient.getIngredientQuantity() - orderDetail.getOrderDetailQuantity();
            ingredient.setIngredientQuantity(newQuantity);
            // 재고 수량 업데이트
            ingredientRepository.save(ingredient);
        }
    }

    // 주문 조회
    @Transactional
    public List<OrderInquiryDTO> inquiryOrder(Long memberKey) {
        // memberKey로 memberEntity 찾기
        MemberEntity memberEntity = memberRepository.findById(memberKey)
                .orElseThrow();

        // 회원의 주문 정보 조회
        List<OrderEntity> orderEntities = orderRepository.findByMemberEntity(memberEntity);

        List<OrderInquiryDTO> orderInquiryDTOList = new ArrayList<>();

        // Entity를 DTO로 변환 -> DTO 새로 만들기 !!
        // 주문 번호, 재료 이름, 총 가격, 주문 날짜
        for (OrderEntity orderEntity : orderEntities) {
            OrderInquiryDTO orderInquiryDTO = convertToOrderInquiryDTO(orderEntity);
            orderInquiryDTOList.add(orderInquiryDTO);
        }
        return orderInquiryDTOList;
    }

    private static OrderInquiryDTO convertToOrderInquiryDTO(OrderEntity orderEntity) {
        List<OrderDetailEntity> orderDetails = orderEntity.getOrderDetails();

        String firstIngredientName = orderDetails.get(0).getIngredientEntity().getIngredientName();

        int size = orderDetails.size() - 1;

        String ingredientName;

        if (size > 0) {
            ingredientName = firstIngredientName + " 외 " + size + "건";
        } else {
            ingredientName = firstIngredientName;
        }

        OrderInquiryDTO orderInquiryDTO = new OrderInquiryDTO();

        orderInquiryDTO.setOrderKey(orderEntity.getOrderKey());
        orderInquiryDTO.setIngredientName(ingredientName);
        orderInquiryDTO.setTotalPrice(orderEntity.getTotalPrice());
        orderInquiryDTO.setOrderDate(orderEntity.getOrderDate());

        return orderInquiryDTO;
    }

    // 주문 상세 조회
    @Transactional
    public OrderDetailsDTO inquiryDetailOrder(Long memberKey, Long orderKey) {
        // memberKey로 memberEntity 찾기
        MemberEntity memberEntity = memberRepository.findById(memberKey)
                .orElseThrow();
        OrderEntity orderEntity = orderRepository.findById(orderKey)
                .orElseThrow();

        // Entity -> DTO
        // 주문 번호, 재료 이름, 재료 가격, 주문 수량, 총 가격, 주문 일자
        // 배송 정보
        // 수령자 이름, 배송지, 핸드폰 번호 -> ???
        OrderDetailsDTO orderDetailsDTO = convertToOrderDetailsDTO(orderEntity);
        return orderDetailsDTO;
    }

    private static OrderDetailsDTO convertToOrderDetailsDTO(OrderEntity orderEntity) {
        List<OrderDetailEntity> orderDetails = orderEntity.getOrderDetails();
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();

        String[] ingredientName = new String[orderDetails.size()];
        int[] ingredientPrice = new int[orderDetails.size()];
        int[] orderQuantity = new int[orderDetails.size()];

        /* 2번 회원이 두부(1) 1개랑 된장(2) 1개를 주문!
         * -> 주문 번호
         *    "두부", "된장"
         *    1500, 3000
         *    1, 1
         *    4500
         *    "2023-11-26" */

        for (int i = 0; i < orderDetails.size(); i++) {
            ingredientName[i] = orderDetails.get(i).getIngredientEntity().getIngredientName();
            orderQuantity[i] = orderDetails.get(i).getOrderDetailQuantity();
            ingredientPrice[i] = orderDetails.get(i).getIngredientEntity().getIngredientPrice() * orderQuantity[i];
        }

        orderDetailsDTO.setOrderKey(orderEntity.getOrderKey());

        for (int i = 0; i < orderDetails.size(); i++) {
            orderDetailsDTO.setIngredientName(ingredientName);
            orderDetailsDTO.setIngredientPrice(ingredientPrice);
            orderDetailsDTO.setOrderDetailQuantity(orderQuantity);
        }

        orderDetailsDTO.setTotalPrice(orderEntity.getTotalPrice());
        orderDetailsDTO.setOrderDate(orderEntity.getOrderDate());

        orderDetailsDTO.setMemberName(orderEntity.getMemberEntity().getMemberName());
        orderDetailsDTO.setMemberPhone(orderEntity.getMemberEntity().getMemberPhone());
        orderDetailsDTO.setMemberAddress(orderEntity.getMemberEntity().getMemberAddress());

        return orderDetailsDTO;
    }

    private void validateOrderQuantity(int orderQuantity, IngredientEntity ingredientEntity) {
        int ingredientQuantity = ingredientEntity.getIngredientQuantity();
        if (ingredientQuantity < orderQuantity) {
            throw new CookeryketException("재료 수량이 부족합니다.\n재료명 : "
                    + ingredientEntity.getIngredientName()
                    + "\n주문 수량 : "
                    + orderQuantity
                    + "개\n현재 재고 : "
                    + ingredientEntity.getIngredientQuantity()
                    + "개");
        }
    }

}
