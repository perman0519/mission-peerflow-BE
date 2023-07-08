package com.peer.missionpeerflow.dto.request.comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerCommentDeleteRequest {

    @NotNull(message = "비밀번호를 입력해주세요.")
    private String password;

    public AnswerCommentDeleteRequest(String password) {
        this.password = password;
    }
}
