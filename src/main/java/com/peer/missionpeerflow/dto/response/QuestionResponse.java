package com.peer.missionpeerflow.dto.response;

import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.util.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionResponse {

    private Long questionId;
    private String title;
    private int answerCount;
    private Category category;
    private Long recommend;
    private Long view;
    private String nickname;
    private LocalDateTime createdAt;
    private String content;

    @Builder
    public QuestionResponse (Long questionId, String title, String content, int answerCount, Category category, Long recommend, Long view, String nickname, LocalDateTime createdAt)
    {
        this.questionId = questionId;
        this.title = title;
        this.answerCount = answerCount;
        this.category = category;
        this.recommend = recommend;
        this.view = view;
        this. nickname = nickname;
        this.createdAt = createdAt;
        this.content = content;
    }

    public static QuestionResponse fromQuestion(Question question){
        return QuestionResponse.builder()
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .answerCount(question.getAnswerList().size())
                .category(question.getCategory())
                .recommend(question.getRecommend())
                .view(question.getView())
                .nickname(question.getNickname())
                .createdAt(question.getCreatedAt())
                .content(question.getContent())
                .build();
    }
}
