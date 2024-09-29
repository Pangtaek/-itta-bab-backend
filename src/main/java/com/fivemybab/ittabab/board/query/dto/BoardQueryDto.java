package com.fivemybab.ittabab.board.query.dto;


import lombok.*;


@Data
public class BoardQueryDto {
    private Long postId;       // 게시물 ID
    private String title;      // 제목
    private String content;    // 내용
    private int likeCount;     // 좋아요 수
    private int commentCount;   // 댓글 수
    private boolean isBlinded;  // 블라인드 여부
    private String createdAt;   // 생성일


}