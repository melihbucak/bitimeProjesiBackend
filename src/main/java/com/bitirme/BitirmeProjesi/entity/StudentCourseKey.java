package com.bitirme.BitirmeProjesi.entity;

import java.io.Serializable;

public class StudentCourseKey implements Serializable {
    private Long student;

    private Long course;

    public StudentCourseKey() {}

    public StudentCourseKey(Long ogrenci, Long dersler) {
        this.student = ogrenci;
        this.course = dersler;
    }
}
