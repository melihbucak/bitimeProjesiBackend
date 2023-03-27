package com.bitirme.BitirmeProjesi.service;

import com.bitirme.BitirmeProjesi.entity.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    User createUser(User user);
    ResponseEntity checkLogin(User user);
    Ogrenci saveOgrenci(Ogrenci ogrenci);
    Ogretmen saveOgretmen(Ogretmen ogretmen);
    Dersler saveDers(Dersler dersler);
    List<Ogrenci> getOgrenciler();
    List<Ogretmen> getOgretmenler();
    List<Dersler> getDersler();
    Ogrenci getOgrenciById(Long id);
    Ogretmen getOgretmenById(Long id);
//    Ogrenci getOgrenciByIdAndDersler(Long id);
    Yoklama saveYoklama(Yoklama yoklama) throws Exception;

}
