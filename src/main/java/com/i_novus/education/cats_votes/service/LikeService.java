package com.i_novus.education.cats_votes.service;

import com.i_novus.education.cats_votes.dto.response.CatStatResponseDto;
import com.i_novus.education.cats_votes.dto.response.LikeResponseDto;
import com.i_novus.education.cats_votes.dto.response.PairResponseDto;

import java.util.List;

public interface LikeService {
    LikeResponseDto postLike(Long pairId, Long catId, Long userId);

    LikeResponseDto deleteLike(Long likeId, Long userId);

    PairResponseDto getNextPair(Long userId);

    List<CatStatResponseDto> getStats();

}
