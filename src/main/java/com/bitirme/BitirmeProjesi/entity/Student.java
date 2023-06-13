package com.bitirme.BitirmeProjesi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ogrenci")
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @SequenceGenerator(name = "SEQ_ogrenci", sequenceName = "SEQ_ogrenci", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ogrenci")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "ogrenciAdi",nullable = false)
    private String ogrenciAdi;
    @Column(name = "ogrenciSoyadi",nullable = false)
    private String ogrenciSoyadi;
    @Column(name = "ogrenci_no",unique = true,nullable = false)
    private Long ogrenci_no;
    @Column(name = "ogrenci_tc",unique = true,nullable = false)
    private Long ogrenci_TC;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}