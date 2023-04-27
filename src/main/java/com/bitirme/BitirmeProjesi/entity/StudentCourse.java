package com.bitirme.BitirmeProjesi.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@IdClass(StudentCourseKey.class)
@Entity
@Table(name = "student_course")
public class StudentCourse implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ogrenci_Id")
    private Student student;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dersKodu")
    private Course course;

    public StudentCourse() {}

    public StudentCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

}
