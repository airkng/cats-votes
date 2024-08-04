package com.i_novus.education.cats_votes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, name = "email")
    private String email;
    @Column(nullable = false)
    private String password;
    @Column()
    private String firstname;
    @Column()
    private String lastname;
    @Embedded()
    private UserMetadata metadata;


}
