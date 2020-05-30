package com.ls.wod.dto;

import lombok.Data;

@Data
public class JwtDTO {

    private String token;

    public JwtDTO() {
    }

    public JwtDTO(String token) {
        this.token = token;
    }
}
