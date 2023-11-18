package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="myfridge")
public class MyfridgeEntity {
    @Id
    @ManyToOne
    @JoinColumn(name="member_number")
    private MemberEntity memberNumber;

    @Id
    @ManyToOne
    @JoinColumn(name="ingredient_number")
    private IngredientEntity ingredientNumber;

}


