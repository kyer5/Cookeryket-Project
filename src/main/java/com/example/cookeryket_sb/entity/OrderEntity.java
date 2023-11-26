package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderKey;

    @ManyToOne
    @JoinColumn(name = "member_key")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetails;

    private String orderDate;

    private int totalPrice;

    public Long getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(Long orderKey) {
        this.orderKey = orderKey;
    }

    public MemberEntity getMemberEntity() {
        return memberEntity;
    }

    public void setMemberEntity(MemberEntity member) {
        this.memberEntity = member;
    }

    public List<OrderDetailEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
        this.orderDetails = orderDetails;

    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}