package com.bitirme.BitirmeProjesi.repository;

import com.bitirme.BitirmeProjesi.entity.Dersler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Dersler,Long> {
    Dersler findByDersKodu(Dersler ders_kodu);
}
