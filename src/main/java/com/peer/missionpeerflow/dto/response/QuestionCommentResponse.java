package com.peer.missionpeerflow.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionCommentResponse extends BaseResponse {
    private String type = "question";
    private long questionId;
}
