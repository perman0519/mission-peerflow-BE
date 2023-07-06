package com.peer.missionpeerflow.dto.response;

import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.entity.QuestionComment;
import com.peer.missionpeerflow.util.Category;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionDetailResponse {

    private String title;
    private Category category;
    private Long recommend;
    private Long view;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String content;
    private List<Answer> answerList;
    private List<QuestionComment> questionCommentList;

    @Builder
    public QuestionDetailResponse (String title, String content, Category category, Long recommend, Long view, String nickname, LocalDateTime createdAt, LocalDateTime updatedAt, List<Answer> answerList, List<QuestionComment> questionCommentList)
    {
        this.title = title;
        this.category = category;
        this.recommend = recommend;
        this.view = view;
        this. nickname = nickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
        this.answerList = answerList;
        this.questionCommentList = questionCommentList;
    }

    public static QuestionDetailResponse fromQuestion(Question question){
        return QuestionDetailResponse.builder()
                .nickname(question.getNickname())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .updatedAt(question.getUpdatedAt())
                .answerList(question.getAnswerList())
                .title(question.getTitle())
                .category(question.getCategory())
                .recommend(question.getRecommend())
                .view(question.getView())
                .build();
    }
}
