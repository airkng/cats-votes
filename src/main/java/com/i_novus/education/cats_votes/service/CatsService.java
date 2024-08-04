package com.i_novus.education.cats_votes.service;

import com.i_novus.education.cats_votes.dto.request.CatRequestDto;
import com.i_novus.education.cats_votes.dto.response.pub.CatPublicResponseDto;
import com.i_novus.education.cats_votes.dto.response.CatResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface CatsService {
    CatResponseDto createCat(CatRequestDto dto, Long userId);

    CatResponseDto updateCat(Long catId, CatRequestDto dto, Long userId);

    CatPublicResponseDto getCat(Long catId);

    void deleteCat(Long catId, Long userId);

    CatResponseDto savePhoto(Long catId, MultipartFile file, Long userId);

    CatResponseDto updateCatPhoto(Long catId, MultipartFile file, Long userId);

    CatResponseDto deletePhoto(Long catId, String photoLink, Long userId);

    CatResponseDto getCatPersonalInfo(Long catId, Long userId);

}
