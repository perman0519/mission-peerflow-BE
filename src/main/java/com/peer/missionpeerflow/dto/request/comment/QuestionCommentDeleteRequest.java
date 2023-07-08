package com.peer.missionpeerflow.dto.request.comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionCommentDeleteRequest {

    @NotNull
    private String password;

    public QuestionCommentDeleteRequest(String password) {
        this.password = password;
    }
}
