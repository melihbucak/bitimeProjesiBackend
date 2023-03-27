package com.bitirme.BitirmeProjesi.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "yoklama")
public class Yoklama {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "fk_ogrenci_Id",referencedColumnName = "ogrenci_Id")
    private Ogrenci ogrenci;

    @ManyToOne
    @JoinColumn(name = "fk_ders_kodu",referencedColumnName = "dersKodu")
    private Dersler dersKodu;

    @Column(name = "tarih")
    private LocalDate tarih;

    @Column(name = "katilim_durumu")
    private Boolean katilimDurumu;

    @Column(name = "devamsizlik_sayisi")
    private int devamsizlikSayisi;

    @Column(name = "izin_sayisi")
    private int izinSayisi;

    public Yoklama() {
    }

    public Yoklama(Ogrenci ogrenci, Dersler dersKodu, LocalDate tarih, Boolean katilimDurumu, int devamsizlikSayisi, int izinSayisi) {
        this.ogrenci = ogrenci;
        this.dersKodu = dersKodu;
        this.tarih = tarih;
        this.katilimDurumu = katilimDurumu;
        this.devamsizlikSayisi = devamsizlikSayisi;
        this.izinSayisi = izinSayisi;
    }
}

