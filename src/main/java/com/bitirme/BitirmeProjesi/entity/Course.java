package com.bitirme.BitirmeProjesi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dersler")
public class Course {
    @Id
    @SequenceGenerator(name = "SEQ_ders_kodu", sequenceName = "SEQ_ders_kodu", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ders_kodu")
    @Column(name = "dersKodu", unique = true, nullable = false)
    private Long dersKodu;

    @Column(name = "dersAdi", unique = true, nullable = false)
    private String dersAdi;

    @ManyToOne()
    @JoinColumn(name = "fk_Ogretmen_Id",referencedColumnName = "ogretmen_Id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonBackReference
    private Teacher teacher;
}