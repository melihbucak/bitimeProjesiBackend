package com.bitirme.BitirmeProjesi.repo;

import com.bitirme.BitirmeProjesi.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
