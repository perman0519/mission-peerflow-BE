package com.peer.missionpeerflow.dto.response;

import com.peer.missionpeerflow.entity.Answer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AnswerResponse {

    private String nickname;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long recommend;
    private Long questionId;
    private Long answerId;
    private boolean adopted;

    @Builder
    public AnswerResponse(String nickname, String content, LocalDateTime createdAt, LocalDateTime updatedAt, Long recommend, Long questionId, Long answerId, boolean adopted) {
        this.nickname = nickname;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.recommend = recommend;
        this.questionId = questionId;
        this.answerId = answerId;
        this.adopted = adopted;
    }

    public static AnswerResponse fromAnswer(Answer answer){
        return AnswerResponse.builder()
                .nickname(answer.getNickname())
                .content(answer.getContent())
                .createdAt(answer.getCreatedAt())
                .updatedAt(answer.getUpdatedAt())
                .recommend(answer.getRecommend())
                .questionId(answer.getQuestion().getQuestionId())
                .answerId(answer.getAnswerId())
                .adopted(answer.getIsAdopted())
                .build();
    }
}
