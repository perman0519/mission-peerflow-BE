package com.peer.missionpeerflow.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerRequest {

    @NotNull
    private Long questionId;
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
    @NotBlank
    private String content;
    @NotNull
    private LocalDateTime createdAt;

    @Builder
    public AnswerRequest(String nickname, String password,  String content, Long questionId, LocalDateTime createdAt)
    {
        this.nickname = nickname;
        this.password = password;
        this.questionId = questionId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
