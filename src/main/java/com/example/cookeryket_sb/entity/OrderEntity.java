//package com.example.cookeryket_sb.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name="order")
//public class OrderEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int orderNumber;
//
//    @ManyToOne
//    private MemberEntity memberNumber;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<OrderDetailEntity> orderDetailNumber;
//}
