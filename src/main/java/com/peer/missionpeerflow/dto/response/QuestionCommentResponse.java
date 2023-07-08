package com.peer.missionpeerflow.dto.response;

import com.peer.missionpeerflow.entity.QuestionComment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionCommentResponse {

    private String type;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long questionCommentId;

    @Builder
    public QuestionCommentResponse(String nickname, String content, LocalDateTime createdAt, LocalDateTime updatedAt, Long questionCommentId) {
        this.type = "QuestionComment";
        this.nickname = nickname;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.questionCommentId = questionCommentId;
    }

    public static QuestionCommentResponse fromQuestion (QuestionComment questionComment){
        return QuestionCommentResponse.builder()
                .nickname(questionComment.getNickname())
                .content(questionComment.getContent())
                .createdAt(questionComment.getCreatedAt())
                .updatedAt(questionComment.getUpdatedAt())
                .questionCommentId(questionComment.getQuestionCommentId())
                .build();
    }
}
