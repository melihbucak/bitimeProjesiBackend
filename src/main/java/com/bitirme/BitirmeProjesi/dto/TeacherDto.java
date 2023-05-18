package com.bitirme.BitirmeProjesi.dto;

import lombok.Data;

@Data
public class TeacherDto {
    private Long id;
    private String ogretmenAdi;
    private String ogretmenSoyadi;
    private String ogretmen_no;
    private Long ogretmen_TC;
    private Long userid;
}
