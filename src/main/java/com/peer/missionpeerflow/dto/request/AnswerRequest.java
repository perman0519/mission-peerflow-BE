package com.peer.missionpeerflow.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerRequest {

    @NotBlank
    private String title;
    @NotBlank
    private Long questionId;
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
    @NotBlank
    private String content;

    private LocalDateTime createdAt;

    @Builder
    public AnswerRequest(String title, String nickname, String password,  String content, Long qusetionId, LocalDateTime createdAt)
    {
        this.title = title;
        this.nickname = nickname;
        this.password = password;
        this.questionId = qusetionId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
