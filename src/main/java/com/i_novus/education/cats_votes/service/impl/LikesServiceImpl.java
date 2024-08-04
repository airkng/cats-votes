package com.i_novus.education.cats_votes.service.impl;

import com.i_novus.education.cats_votes.dto.response.CatStatResponseDto;
import com.i_novus.education.cats_votes.dto.response.LikeResponseDto;
import com.i_novus.education.cats_votes.dto.response.PairResponseDto;
import com.i_novus.education.cats_votes.service.LikeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikesServiceImpl implements LikeService {
    @Override
    public LikeResponseDto postLike(Long pairId, Long catId, Long userId) {
        return null;
    }

    @Override
    public LikeResponseDto deleteLike(Long likeId, Long userId) {
        return null;
    }

    @Override
    public PairResponseDto getNextPair(Long userId) {
        return null;
    }

    @Override
    public List<CatStatResponseDto> getStats() {
        return null;
    }
}
