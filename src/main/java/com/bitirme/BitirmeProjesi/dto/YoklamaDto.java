package com.bitirme.BitirmeProjesi.dto;

import com.bitirme.BitirmeProjesi.entity.Course;
import lombok.Data;

@Data
public class YoklamaDto {
    private int devamsizlikSayisi;
    private int izinSayisi;
    private Long studentId;
    private Long dersKodu;
}
