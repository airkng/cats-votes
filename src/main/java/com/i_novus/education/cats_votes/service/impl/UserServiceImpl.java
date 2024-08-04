package com.i_novus.education.cats_votes.service.impl;

import com.i_novus.education.cats_votes.dto.request.UserRequestDto;
import com.i_novus.education.cats_votes.dto.response.pub.UserPublicResponseDto;
import com.i_novus.education.cats_votes.dto.response.UserResponseDto;
import com.i_novus.education.cats_votes.mappers.UserMapper;
import com.i_novus.education.cats_votes.model.User;
import com.i_novus.education.cats_votes.model.UserMetadata;
import com.i_novus.education.cats_votes.repository.UserRepository;
import com.i_novus.education.cats_votes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserResponseDto createUser(final UserRequestDto dto) {
        User user = mapper.toUser(dto);
        UserMetadata metadata = UserMetadata.builder()
                .created(LocalDateTime.now())
                .build();
        user.setMetadata(metadata);
        return mapper.toUserResponseDto(repository.save(user));
    }

    @Override
    public UserResponseDto getMyAccountInfo(Long userId) {
        //todo: добавить кэширование
        return null;
    }

    @Override
    public UserResponseDto updateOrCreateUser(UserRequestDto dto, Long userId) {
        //todo: добавить кэширование
        Optional<User> userOptional = repository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            User newuser = mapper.toUser(dto);
            newuser.setMetadata(
                    UserMetadata.builder()
                            .created(user.getMetadata().getCreated())
                            .modified(LocalDateTime.now())
                            .deleted(null) //todo:
                            .build()
            );
        }
        return null;
    }

    @Override
    public UserResponseDto updateExistUser(UserRequestDto dto, Long userId) {
        //todo: добавить кэширование
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public UserPublicResponseDto getUser(Long userId, Long userId1) {
        return null;
    }
}
