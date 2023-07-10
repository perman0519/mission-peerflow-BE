package com.peer.missionpeerflow.dto.response;

import com.peer.missionpeerflow.entity.AnswerComment;
import com.peer.missionpeerflow.entity.QuestionComment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AnswerCommentResponse {

    private String type;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long answerCommentId;

    @Builder
    public AnswerCommentResponse(String nickname, String content, LocalDateTime createdAt, LocalDateTime updatedAt, Long answerCommentId) {
        this.type = "AnswerComment";
        this.nickname = nickname;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.answerCommentId = answerCommentId;
    }

    public static AnswerCommentResponse fromAnswer (AnswerComment answerComment){
        return AnswerCommentResponse.builder()
                .nickname(answerComment.getNickname())
                .content(answerComment.getContent())
                .createdAt(answerComment.getCreatedAt())
                .updatedAt(answerComment.getUpdatedAt())
                .answerCommentId(answerComment.getAnswerCommentId())
                .build();
    }
}
