package com.example.cookeryket_sb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyfridgeEntity {
    @Id
    @ManyToOne
    private MemberEntity memberNumber;

    @Id
    @ManyToOne
    private IngredientEntity ingredientNumber;

}


