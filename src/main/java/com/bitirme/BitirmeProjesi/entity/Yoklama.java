package com.bitirme.BitirmeProjesi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "yoklama")
public class Yoklama {
    @Id
    @SequenceGenerator(name = "SEQ_yoklama_id_seq", sequenceName = "SEQ_yoklama_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_yoklama_id_seq")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_ogrenci_Id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "fk_ders_kodu", referencedColumnName = "dersKodu")
    private Course dersKodu;


    @Column(name = "devamsizlik_sayisi")
    private int devamsizlikSayisi;

    @Column(name = "izin_sayisi")
    private int izinSayisi;
}

