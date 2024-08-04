package com.i_novus.education.cats_votes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatResponseDto {
    private Long id;
    private String name;
    private String age;
    private String description;


}
