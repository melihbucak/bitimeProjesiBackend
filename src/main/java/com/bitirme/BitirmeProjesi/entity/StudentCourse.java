package com.bitirme.BitirmeProjesi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
@IdClass(StudentCourseKey.class)
@Entity
@Table(name = "student_course")
public class StudentCourse implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ogrenci_Id")
    private Ogrenci ogrenci;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dersKodu")
    private Dersler dersler;

    public StudentCourse() {}

    public StudentCourse(Ogrenci ogrenci, Dersler dersler) {
        this.ogrenci = ogrenci;
        this.dersler = dersler;
    }

}
