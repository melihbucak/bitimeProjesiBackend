package com.bitirme.BitirmeProjesi.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "admin")
public class Admin {
    @Id
    @SequenceGenerator(name = "SEQ_admin", sequenceName = "SEQ_admin", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_admin")
    private Long id;
    @Column(name = "username", unique = true,nullable = false)
    private String username;
    @Column(name = "password", unique = true,nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}

