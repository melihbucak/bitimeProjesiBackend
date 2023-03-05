package com.bitirme.BitirmeProjesi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ogrenci")
public class Ogrenci {
    @Id
    @SequenceGenerator(name = "SEQ_ogrenci", sequenceName = "SEQ_ogrenci", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ogrenci")
    @Column(name = "ogrenci_Id", unique = true, nullable = false)
    private Long ogrenci_Id;
    @Column(name = "ogrenciAdi")
    private String ogrenciAdi;
    @Column(name = "ogrenciSoyadi")
    private String ogrenciSoyadi;


//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "ogrenci_ders_tbl",
//            joinColumns = {
//                    @JoinColumn(name = "student_Id", referencedColumnName = "ogrenci_Id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "course_Id", referencedColumnName = "ders_kodu")
//            })
//    private List<Dersler> derslers;


}
