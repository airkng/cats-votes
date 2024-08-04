package com.i_novus.education.cats_votes.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class UserMetadata {
    private LocalDateTime created;
    private LocalDateTime deleted;
    private LocalDateTime modified;
}
