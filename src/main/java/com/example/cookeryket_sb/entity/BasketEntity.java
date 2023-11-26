package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name="basket")
public class BasketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketKey;

    @ManyToOne
    @JoinColumn(name = "member_key")
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name = "ingredient_key")
    private IngredientEntity ingredientEntity;

    private int basketQuantity;

    public BasketEntity() {
    }

    @Builder
    public BasketEntity(Long basketKey, MemberEntity memberEntity, IngredientEntity ingredientEntity, int basketQuantity) {
        this.basketKey = basketKey;
        this.memberEntity = memberEntity;
        this.ingredientEntity = ingredientEntity;
        this.basketQuantity = basketQuantity;
    }

    public Long getBasketKey() {
        return basketKey;
    }

    public void setBasketKey(Long basketKey) {
        this.basketKey = basketKey;
    }

    public MemberEntity getMemberEntity() {
        return memberEntity;
    }

    public void setMemberEntity(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }

    public IngredientEntity getIngredientEntity() {
        return ingredientEntity;
    }

    public void setIngredientEntity(IngredientEntity ingredientEntity) {
        this.ingredientEntity = ingredientEntity;
    }

    public int getBasketQuantity() {
        return basketQuantity;
    }

    public void setBasketQuantity(int basketQuantity) {
        this.basketQuantity = basketQuantity;
    }

    public void increaseQuantity(int basketQuantity) {
        this.basketQuantity += basketQuantity;
    }
}
