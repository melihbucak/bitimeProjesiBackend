package com.bitirme.BitirmeProjesi.repo;

import com.bitirme.BitirmeProjesi.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
