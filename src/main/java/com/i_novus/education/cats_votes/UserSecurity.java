package com.i_novus.education.cats_votes;

import lombok.*;

import java.security.Principal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserSecurity implements Principal {
    private Long id;
    private String name;
}
