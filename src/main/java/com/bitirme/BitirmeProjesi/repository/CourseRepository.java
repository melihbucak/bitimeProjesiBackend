package com.bitirme.BitirmeProjesi.repository;

import com.bitirme.BitirmeProjesi.entity.Dersler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Dersler,Long> {
}
