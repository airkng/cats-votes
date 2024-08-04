package com.i_novus.education.cats_votes.controllers;

import com.i_novus.education.cats_votes.dto.response.CatStatResponseDto;
import com.i_novus.education.cats_votes.dto.response.LikeResponseDto;
import com.i_novus.education.cats_votes.dto.response.PairResponseDto;
import com.i_novus.education.cats_votes.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.i_novus.education.cats_votes.Commons.getUserId;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService service;

    @Operation(
            summary = "Осуществляется лайк определенной кошке.",
            description = "Для того, чтобы отправить лайк, нужно получить пару. Пара - это связка кошек между собой, список" +
                    "для пар голосования"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Лайк успешно сохранился"),
            @ApiResponse(responseCode = "404", description = "Лайк не найден"),
            @ApiResponse(responseCode = "409", description = "Конфликт лайков. Лайк уже был осуществлен до этого")
    }
    )
    @PostMapping("/{pairId}/{catId}")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponseDto likeToCat(
            @Parameter(name = "pairId",
                    description = "Айдишник пары. Пара - это независимая часть которая есть между каждыми кошками. совокупность " +
                            "пар -  это всевозможные связки между кошками") @PathVariable Long pairId,
            @Parameter(name = "catId", description = "Уникальный идентификатор кошки") @PathVariable Long catId
    ) {
        return service.postLike(pairId, catId, getUserId());
    }


    @Operation(
            summary = "Осуществляется отмена лайка определенной кошке.",
            description = "Для того, чтобы отменить лайк, нужен уникальный идентификатор лайка"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Лайк успешно удален"),
            @ApiResponse(responseCode = "404", description = "Лайк не найден"),
            @ApiResponse(responseCode = "409", description = "Пользователь не ставил лайк"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")

    }
    )
    @DeleteMapping("/{likeId}")
    public LikeResponseDto discardLike(@PathVariable Long likeId) {
        return service.deleteLike(likeId, getUserId());
    }

    @Operation(
            summary = "Получение следующей возможной пары кошечек для пользователя ",
            description = "Повторение пары невозможно. Каждая пара должна быть уникальной"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пара успешно отправлена"),
            @ApiResponse(responseCode = "404", description = "Лайк не найден")
    }
    )
    @GetMapping("/nextPair")
    public PairResponseDto getCatsPair() {
        return service.getNextPair(getUserId());
    }

    @Operation(
            summary = "Получение статистики голосования за лучшую кошечку ",
            description = "Получение статистики: количество голосов"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пара успешно отправлена"),
            @ApiResponse(responseCode = "404", description = "Лайк не найден")
    }
    )
    @GetMapping("/stats")
    public List<CatStatResponseDto> getStats() {
        return service.getStats();
    }

}
