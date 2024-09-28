package com.fivemybab.ittabab.store.command.application.service;

import com.fivemybab.ittabab.store.command.application.dto.CreateStoreReviewDto;
import com.fivemybab.ittabab.store.command.application.dto.UpdateStoreReviewDto;
import com.fivemybab.ittabab.store.command.application.repository.StoreReviewRepository;
import com.fivemybab.ittabab.store.command.domain.aggregate.StoreReview;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreReviewService {

    private final StoreReviewRepository repository;
    private final ModelMapper modelMapper;

    /* 가게 리뷰 추가 */
    @Transactional
    public void registStoreReview(CreateStoreReviewDto newStoreReview) {
        repository.save(modelMapper.map(newStoreReview, StoreReview.class));
    }

    /* 가게 리뷰 수정*/
    @Transactional
    public void updateStoreReview(Long reviewId, UpdateStoreReviewDto updateStoreReviewDTO) {
        StoreReview storeReview = repository.findById(reviewId)
                .orElseThrow(()-> new IllegalArgumentException("리뷰를 찾지 못했습니다."));




        if(updateStoreReviewDTO.getReviewContent() != null) {
            storeReview.modifyStoreReviewContent(updateStoreReviewDTO.getReviewContent());
        }

        // 만약 평점에 값을 입력하지 않을 경우 -> 예외처리 추가할 것
        storeReview.modifyStoreReviewRating(updateStoreReviewDTO.getRating());



    }

    /* 가게 리뷰 삭제 */
    @Transactional
    public void deleteStoreReview(Long storeReviewId) {
        repository.deleteById(storeReviewId);
    }


}
