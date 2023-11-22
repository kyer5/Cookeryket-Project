package com.example.cookeryket_sb.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private Long memberId;
    private List<Long> ingredientIds;
    private List<Integer> orderDetailCounts;

}
