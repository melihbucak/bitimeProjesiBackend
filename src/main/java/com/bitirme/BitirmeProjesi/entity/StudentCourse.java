package com.bitirme.BitirmeProjesi.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "student_course")
public class StudentCourse implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ogrenci_Id")
    private Ogrenci ogrenci;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ders_Kodu")
    private Dersler dersler;

    public StudentCourse() {}

    public StudentCourse(Ogrenci ogrenci, Dersler dersler) {
        this.ogrenci = ogrenci;
        this.dersler = dersler;
    }

    // getters and setters
}
