package com.peer.missionpeerflow.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerCommentResponse extends BaseResponse {
    private String type = "answer";
    private long answerId;
}
