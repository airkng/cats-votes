package com.i_novus.education.cats_votes.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "tcat")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Long id;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private String description;
    @Column
    private LocalDateTime created;
    @Column
    private LocalDateTime deleted;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToMany
    List<Image> images;

}
