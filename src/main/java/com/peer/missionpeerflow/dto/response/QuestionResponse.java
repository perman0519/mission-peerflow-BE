package com.peer.missionpeerflow.dto.response;

import com.peer.missionpeerflow.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class QuestionResponse extends BaseResponse {
    private String type = "question";
    private String title;
    private String category;
    private long recommend;
    private long views;
    List<AnswerResponse> answerList;
}
