package com.bitirme.BitirmeProjesi.entity;

import com.bitirme.BitirmeProjesi.Enum.UserType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "kullanici")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_user")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserType role;

}
