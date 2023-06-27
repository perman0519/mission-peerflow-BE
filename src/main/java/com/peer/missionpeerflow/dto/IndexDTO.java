package com.peer.missionpeerflow.dto;

import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.util.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IndexDTO {
    private Long questionId;

    private String title;

    private Integer answerCount;

    private Category category;

    private Long recommend = 0L;

    private Long view = 0L;

    private String nickname;

    private LocalDateTime createdAt;

    public static IndexDTO toIndexDTO(Question question)
    {
        return new IndexDTO(
                question.getQuestionId(),
                question.getTitle(),
                question.getAnswerList().size(),
                question.getCategory(),
                question.getRecommend(),
                question.getView(),
                question.getNickname(),
                question.getCreatedAt()
        );
    }
}
