package com.bitirme.BitirmeProjesi.service;

import com.bitirme.BitirmeProjesi.entity.Dersler;
import com.bitirme.BitirmeProjesi.entity.Ogrenci;
import com.bitirme.BitirmeProjesi.entity.Ogretmen;

public interface UserService {
    Ogrenci saveOgrenci(Ogrenci ogrenci);
    Ogretmen saveOgretmen(Ogretmen ogretmen);
    Dersler saveDers(Dersler dersler);
}
