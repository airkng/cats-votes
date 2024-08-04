package com.i_novus.education.cats_votes.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
public class UserMetadata {
    private LocalDateTime created;
    private LocalDateTime deleted;
    private LocalDateTime modified;
}
