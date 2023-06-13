package com.bitirme.BitirmeProjesi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "konum")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enlem", nullable = false)
    private String enlem;

    @Column(name = "boylam", nullable = false)
    private String boylam;

    @Column(name = "binaAdi")
    private String binaAdi;

}
