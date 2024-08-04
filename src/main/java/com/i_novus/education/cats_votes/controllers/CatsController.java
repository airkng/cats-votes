package com.i_novus.education.cats_votes.controllers;

import com.i_novus.education.cats_votes.dto.request.CatRequestDto;
import com.i_novus.education.cats_votes.dto.response.CatPublicResponseDto;
import com.i_novus.education.cats_votes.dto.response.CatResponseDto;
import com.i_novus.education.cats_votes.service.CatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.i_novus.education.cats_votes.Commons.getUserId;

@RestController
@RequestMapping("/cats")
@RequiredArgsConstructor
@Tag(name = "Cats API", description = "API для CRUD операций с кошками")
public class CatsController {

    private final CatsService service;

    @Operation(summary = "Создание кошечки.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Кошечка успешно создана", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CatResponseDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Кошечка уже существует"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CatResponseDto createCat(@RequestBody CatRequestDto dto) {
        return service.createCat(dto, getUserId());
    }


    @Operation(summary = "Частичное изменение кошечки.", description = "Действие может осуществлять только владелец")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные кошечки успешно обновлены", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CatResponseDto.class))
            }),
            @ApiResponse(responseCode = "409", description = "Кошечка уже существует")
    }
    )
    @PatchMapping("/{catId}")
    public CatResponseDto updateExistsCat(
            @Parameter(description = "Уникальный идентификатор кошки") @PathVariable Long catId,
            @Parameter(description = "Данные для обновления сущности") @RequestBody CatRequestDto dto
    ) {
        return service.updateCat(catId, dto, getUserId());
    }

    @Operation(summary = "Удаление кошечки.", description = "Действие может осуществлять только владелец")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные кошечки успешно удалены", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CatResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Кошечка не найдена")
    }
    )
    @DeleteMapping("/{catId}/delete")
    public void deleteCat(@Parameter(description = "Уникальный идентификатор кошки") @PathVariable Long catId) {
        service.deleteCat(catId, getUserId());
    }

    @Operation(summary = "Публичное получение данных кошечки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные кошечки успешно отправлены", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CatResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Кошечка не найдена")
    }
    )
    @GetMapping("/{catId}")
    public CatPublicResponseDto getCat(@PathVariable Long catId) {
        return service.getCat(catId);
    }

    @Operation(summary = "Полное персональное получение данных кошечки", description = "Может получить только владелец")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные кошечки успешно отправлены", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CatResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Кошечка не найдена")
    }
    )
    @GetMapping("/{catId}/info")
    public CatResponseDto getCatInfo(@Parameter(description = "Уникальный идентификатор кошки") @PathVariable Long catId) {
        return service.getCatPersonalInfo(catId, getUserId());
    }

    @Operation(summary = "Загрузка изображения определенной кошки.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "301", description = "Изображение успешно загружено"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "404", description = "Кошечка не найдена")
    }
    )
    @PostMapping("{catId}/UploadImage")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public String uploadPhoto(
            @Parameter(name = "catId", description = "Уникальный идентификатор кошки") @PathVariable Long catId,
            @Parameter(name = "imageFile", description = "Изображение") @RequestParam("imageFile") MultipartFile file,
            RedirectAttributes redirectAttributes
    ) {
        service.savePhoto(catId, file, getUserId());
        redirectAttributes.addAttribute("catId", catId).addFlashAttribute("message", "Image: " + file.getOriginalFilename() + " was  successful uploaded.");
        return "redirect:/cats/{catId}";

    }

    @Operation(summary = "Обновление изображения определенной кошки.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "301", description = "Изображение успешно загружено"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "404", description = "Кошечка не найдена")
    }
    )
    @PutMapping("{catId}/UpdateImage")
    public String updatePhoto(
            @Parameter(description = "Уникальный идентификатор кошки") @PathVariable Long catId,
            @Parameter(description = "Передаваемое изображение") @RequestParam("imageFile") MultipartFile file,
            RedirectAttributes redirectAttributes
    ) {
        service.updateCatPhoto(catId, file, getUserId());
        redirectAttributes.addAttribute("catId", catId).addFlashAttribute("message", "Image: " + file.getOriginalFilename() + " was  successful uploaded.");
        return "redirect:/cats/{catId}";
    }

    @Operation(summary = "Удаление изображения определенной кошки.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображение успешно удалено"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "404", description = "Кошечка не найдена")
    }
    )
    @DeleteMapping("{catId}/photo/delete/{photoId}")
    public void deletePhoto(
            @PathVariable Long catId,
            @PathVariable(name = "photoId") String photoLink
    ) {
        service.deletePhoto(catId, photoLink, getUserId());
    }

}
