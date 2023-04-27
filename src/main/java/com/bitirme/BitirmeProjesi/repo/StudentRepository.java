package com.bitirme.BitirmeProjesi.repo;

import com.bitirme.BitirmeProjesi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
