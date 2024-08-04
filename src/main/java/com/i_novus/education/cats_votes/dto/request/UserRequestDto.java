package com.i_novus.education.cats_votes.dto.request;

import com.i_novus.education.cats_votes.service.validation.ModificationInfo;
import com.i_novus.education.cats_votes.service.validation.RegistrationInfo;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    @Email(groups = {ModificationInfo.class, RegistrationInfo.class})
    @NotNull(groups = RegistrationInfo.class)
    private String email;
    @Size(min = 4, groups = RegistrationInfo.class)
    @NotNull(groups = RegistrationInfo.class)
    private String password;
    @Nullable
    private String firstname;
    private String lastname;
}
