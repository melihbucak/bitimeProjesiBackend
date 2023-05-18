package com.bitirme.BitirmeProjesi.repo;

import com.bitirme.BitirmeProjesi.entity.Course;
import com.bitirme.BitirmeProjesi.entity.Yoklama;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface YoklamaRepository extends JpaRepository<Yoklama, Long> {
    List<Yoklama> findByStudentId(Long studentId);
//    <Yoklama> getByStudentId(Long ogrenciId);
}
