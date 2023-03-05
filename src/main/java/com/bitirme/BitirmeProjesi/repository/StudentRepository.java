package com.bitirme.BitirmeProjesi.repository;

import com.bitirme.BitirmeProjesi.entity.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Ogrenci,Long> {
}
