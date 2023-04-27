package com.bitirme.BitirmeProjesi.dto;

import lombok.Data;

@Data
public class AdminDto {
    private Long id;
    private String username;
    private String password;
    private String oldPassword;
    private String newPassword;
}