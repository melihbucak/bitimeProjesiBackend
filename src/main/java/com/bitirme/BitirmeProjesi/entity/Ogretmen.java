package com.bitirme.BitirmeProjesi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "ogretmen")
public class Ogretmen {
    @Id
    @SequenceGenerator(name = "SEQ_ogretmen_Id", sequenceName = "SEQ_ogretmen_Id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ogretmen_Id")
    @Column(name = "ogretmen_Id", unique = true, nullable = false)
    private Long ogretmen_Id;
    @Column(name = "ogretmenAdi" )
    private String ogretmenAdi;
    @Column(name = "ogretmenSoyadi" )
    private String ogretmenSoyadi;

    @JsonManagedReference
    @OneToMany(mappedBy="ogretmen",orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Dersler> derslers;
}
