package com.bitirme.BitirmeProjesi.entity;

import java.io.Serializable;

public class StudentCourseKey implements Serializable {
    private Long ogrenci;

    private Long dersler;

    public StudentCourseKey() {}

    public StudentCourseKey(Long ogrenci, Long dersler) {
        this.ogrenci = ogrenci;
        this.dersler = dersler;
    }
}
