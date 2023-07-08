package com.peer.missionpeerflow.dto.request.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerCommentModifyRequest {

    @NotNull(message = "제목ID를 입력해주세요.")
    private Long answerId;
    @NotBlank(message = "타입을 입력해주세요.")
    private String type;
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    @NotNull(message = "업데이트일을 입력해주세요.")
    private LocalDateTime updatedAt;

    @Builder
    public AnswerCommentModifyRequest (LocalDateTime updatedAt, Long answerId, String type, String nickname, String password, String content)
    {
        this.answerId = answerId;
        this.type = type;
        this.nickname = nickname;
        this.password = password;
        this.content = content;
        this.updatedAt = updatedAt;
    }
}