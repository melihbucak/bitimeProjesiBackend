package com.bitirme.BitirmeProjesi.repo;

import com.bitirme.BitirmeProjesi.entity.Course;
import com.bitirme.BitirmeProjesi.entity.Yoklama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface YoklamaRepository extends JpaRepository<Yoklama, Long> {
    List<Yoklama> findByStudentId(Long studentId);

    List<Yoklama> findByDersKodu_DersKodu(Long dersKodu);

}
