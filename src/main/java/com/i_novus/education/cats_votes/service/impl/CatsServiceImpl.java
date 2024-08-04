package com.i_novus.education.cats_votes.service.impl;

import com.i_novus.education.cats_votes.dto.request.CatRequestDto;
import com.i_novus.education.cats_votes.dto.response.pub.CatPublicResponseDto;
import com.i_novus.education.cats_votes.dto.response.CatResponseDto;
import com.i_novus.education.cats_votes.service.CatsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CatsServiceImpl implements CatsService {
    @Override
    public CatResponseDto createCat(CatRequestDto dto, Long userId) {
        return null;
    }

    @Override
    public CatResponseDto updateCat(Long catId, CatRequestDto dto, Long userId) {
        return null;
    }

    @Override
    public CatPublicResponseDto getCat(Long catId) {
        return null;
    }

    @Override
    public void deleteCat(Long catId, Long userId) {

    }

    @Override
    public CatResponseDto savePhoto(Long catId, MultipartFile file, Long userId) {
        return null;
    }

    @Override
    public CatResponseDto updateCatPhoto(Long catId, MultipartFile file, Long userId) {
        return null;
    }

    @Override
    public CatResponseDto deletePhoto(Long catId, String photoLink, Long userId) {
        return null;
    }

    @Override
    public CatResponseDto getCatPersonalInfo(Long catId, Long userId) {
        return null;
    }
}
