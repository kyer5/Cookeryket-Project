package com.example.cookeryket_sb.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OrderDTO {
    private Long orderKey;
    private String orderDate;
    private int totalPrice;
    private List<OrderDetailDTO> orderDetails;
}
