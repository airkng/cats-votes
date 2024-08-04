package com.i_novus.education.cats_votes.service;

import com.i_novus.education.cats_votes.dto.request.UserRequestDto;
import com.i_novus.education.cats_votes.dto.response.pub.UserPublicResponseDto;
import com.i_novus.education.cats_votes.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);

    UserResponseDto getMyAccountInfo(Long userId);

    UserResponseDto updateOrCreateUser(UserRequestDto dto, Long userId);

    UserResponseDto updateExistUser(UserRequestDto dto, Long userId);


    void deleteUser(Long userId);

    UserPublicResponseDto getUser(Long userId, Long userId1);

}
