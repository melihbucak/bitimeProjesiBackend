package com.bitirme.BitirmeProjesi.repository;

import com.bitirme.BitirmeProjesi.entity.Ogretmen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Ogretmen,Long> {
}
