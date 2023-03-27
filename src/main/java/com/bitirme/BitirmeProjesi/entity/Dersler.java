package com.bitirme.BitirmeProjesi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dersler")
public class Dersler {
    @Id
    @SequenceGenerator(name = "SEQ_ders_Kodu", sequenceName = "SEQ_ders_Kodu", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ders_Kodu")
    @Column(name = "dersKodu", unique = true, nullable = false)
    private Long dersKodu;

    @Column(name = "dersAdi")
    private String dersAdi;

    @ManyToOne()
    @JoinColumn(name = "fk_Ogretmen_Id",referencedColumnName = "ogretmen_Id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonBackReference
    private Ogretmen ogretmen;
}
