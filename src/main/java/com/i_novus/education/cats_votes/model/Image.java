package com.i_novus.education.cats_votes.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "timage")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String imageLink;
    //todo: подумать как хранить данные в бд
    private String imageBlob;
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;

}
