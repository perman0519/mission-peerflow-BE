package com.peer.missionpeerflow.dto.request.question;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionDeleteRequest {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
