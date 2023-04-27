package com.bitirme.BitirmeProjesi.repo;

import com.bitirme.BitirmeProjesi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT u FROM User u WHERE username = :username")
    Optional<User> findByUsername(String username);
}
