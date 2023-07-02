package com.peer.missionpeerflow.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class AnswerCommentRequest {

    @NotNull(message = "답변 글의 id가 필요합니다.")
    private long answerId;

    private String type;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotNull(message = "내용을 입력해주세요.")
    private String content;

    private LocalDate createdAt;
}
