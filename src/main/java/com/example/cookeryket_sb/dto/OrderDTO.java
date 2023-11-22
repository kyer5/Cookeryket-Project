package com.example.cookeryket_sb.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OrderDTO {
    private Long orderNumber;
    private String orderDate;
    private int totalPrice;
    private List<OrderDetailDTO> orderDetails;
}
