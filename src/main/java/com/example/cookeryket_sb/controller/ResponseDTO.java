package com.example.cookeryket_sb.controller;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseDTO {
    private int status;
    private String message;

    @Builder
    public ResponseDTO(int status, String message){
        this.status=status;
        this.message=message;
    }
}
