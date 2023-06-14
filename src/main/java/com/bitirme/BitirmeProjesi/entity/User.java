package com.bitirme.BitirmeProjesi.entity;

import com.bitirme.BitirmeProjesi.enums.UserType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kullanici")
public class User {
    @Id
    @SequenceGenerator(name = "SEQ_user", sequenceName = "SEQ_user", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_user")
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserType role;
}

//    @JsonManagedReference
//    @OneToOne(mappedBy = "user")
//    private Student student;

