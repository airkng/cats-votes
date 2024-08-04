package com.i_novus.education.cats_votes.mappers;

import com.i_novus.education.cats_votes.dto.request.UserRequestDto;
import com.i_novus.education.cats_votes.dto.response.UserResponseDto;
import com.i_novus.education.cats_votes.dto.response.pub.UserPublicResponseDto;
import com.i_novus.education.cats_votes.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toUser(UserRequestDto dto);
    UserResponseDto toUserResponseDto(User user);
    UserPublicResponseDto toUserPublicResponseDto(User user);
}
