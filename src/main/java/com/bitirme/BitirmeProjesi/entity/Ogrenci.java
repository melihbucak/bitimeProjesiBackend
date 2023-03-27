package com.bitirme.BitirmeProjesi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
}
