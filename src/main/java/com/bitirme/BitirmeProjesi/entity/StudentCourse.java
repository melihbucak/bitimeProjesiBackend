
package com.bitirme.BitirmeProjesi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@IdClass(StudentCourseKey.class)
@Entity
@Data
@Table(name = "student_course")
public class StudentCourse implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
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

