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
public class QuestionResponse {

    private Long questionId;
    private String title;
    private int answerCount;
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
    public QuestionResponse (Long questionId, String title, String content, int answerCount, Category category, Long recommend, Long view, String nickname, LocalDateTime createdAt, LocalDateTime updatedAt, List<Answer> answerList, List<QuestionComment> questionCommentList)
    {
        this.questionId = questionId;
        this.title = title;
        this.answerCount = answerCount;
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

    public static QuestionResponse fromQuestion(Question question){
        return QuestionResponse.builder()
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .answerCount(0)
                .category(question.getCategory())
                .recommend(question.getRecommend())
                .view(question.getView())
                .nickname(question.getNickname())
                .createdAt(question.getCreatedAt())
                .content(question.getContent())
                .build();
    }
    public static QuestionResponse fromQuestionwithDetail(Question question){
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
                .updatedAt(question.getUpdatedAt())
                .answerList(question.getAnswerList())
                .questionCommentList(question.getQuestionCommentList())
                .build();
    }

}
