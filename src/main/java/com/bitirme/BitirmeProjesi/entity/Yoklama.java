package com.bitirme.BitirmeProjesi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "yoklama")
public class Yoklama {
    @Id
    @SequenceGenerator(name = "SEQ_yoklama_id_seq", sequenceName = "SEQ_yoklama_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_yoklama_id_seq")
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "fk_ogrenci_Id",referencedColumnName = "ogrenci_Id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "fk_ders_kodu",referencedColumnName = "dersKodu")
    private Course dersKodu;

    @Column(name = "tarih")
    private LocalDate tarih;

    @Column(name = "katilim_durumu")
    private Boolean katilimDurumu;

    @Column(name = "devamsizlik_sayisi")
    private int devamsizlikSayisi;

    @Column(name = "izin_sayisi")
    private int izinSayisi;

}

