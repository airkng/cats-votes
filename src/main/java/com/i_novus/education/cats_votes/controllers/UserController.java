package com.i_novus.education.cats_votes.controllers;

import com.i_novus.education.cats_votes.dto.response.UserPublicResponseDto;
import com.i_novus.education.cats_votes.service.UserService;
import com.i_novus.education.cats_votes.dto.request.UserRequestDto;
import com.i_novus.education.cats_votes.dto.response.UserResponseDto;
import com.i_novus.education.cats_votes.dto.response.pub.UserPublicResponseDto;
import com.i_novus.education.cats_votes.service.UserService;
import com.i_novus.education.cats_votes.service.validation.ModificationInfo;
import com.i_novus.education.cats_votes.service.validation.RegistrationInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.i_novus.education.cats_votes.Commons.getUserId;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "User API", description = "Пользовательское API для CRUD-операций")
public class UserController {

    private final UserService service;

    @Operation(summary = "Создание пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно создан", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Пользователь уже существует"),
            @ApiResponse(responseCode = "400", description = "Ошибка пользовательских данных")
        }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@Parameter(description = "Данные для регистрации") @RequestBody @Validated(RegistrationInfo.class) UserRequestDto dto) {
        return service.createUser(dto);
    }

    @Operation(summary = "Получение персональных данных пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно найден", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
            }),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    }
    )
    @GetMapping("/account")
    public UserResponseDto getUserInfo() {
        return service.getMyAccountInfo(getUserId());
    }

    @Operation(summary = "Получение публичных данных некоторого пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно найден", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Пользователь не был найден"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    }
    )
    @GetMapping("/{userId}")
    public UserPublicResponseDto getSomeUser(@Parameter(description = "айди пользователя, которого ищем") @PathVariable Long userId) {
        return service.getUser(userId , getUserId());
    }

    @Operation(summary = "Обновление пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
            }),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    }
    )
    @PutMapping
    public UserResponseDto updateOrCreateUser(
            @Parameter(description = "Данные для обновления")
            @RequestBody @Validated(ModificationInfo.class) UserRequestDto dto
    ) {
        return service.updateOrCreateUser(dto, getUserId());
    }

    @Operation(summary = "Частичное обновление данных пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
            }),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
    }
    )
    @PatchMapping
    public UserResponseDto updateExistUser(@RequestBody @Valid UserRequestDto dto) { //todo: интересно, нормально ли отработает, потому что валидации нет
        return service.updateExistUser(dto, getUserId());
    }

    @Operation(summary = "Удаление пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно удален", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
            }),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    }
    )
    @DeleteMapping("/delete")
    public void deleteUser() {
        service.deleteUser(getUserId());
    }

}
