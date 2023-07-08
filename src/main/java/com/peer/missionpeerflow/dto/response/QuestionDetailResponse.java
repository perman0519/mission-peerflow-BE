package com.peer.missionpeerflow.dto.response;

import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.util.Category;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionDetailResponse {

    private String type;
    private String title;
    private Category category;
    private Long recommend;
    private Long view;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String content;
    private List<AnswerResponse> answerResponseList;

    @Builder
    public QuestionDetailResponse (String type, String title, String content, Category category, Long recommend, Long view, String nickname, LocalDateTime createdAt, LocalDateTime updatedAt, List<AnswerResponse> answerResponseList)
    {
        this.type = type;
        this.title = title;
        this.category = category;
        this.recommend = recommend;
        this.view = view;
        this. nickname = nickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
        this.answerResponseList = answerResponseList;
    }

     public static List<AnswerResponse> convertToAnswerResponseList(List<Answer> answerList) {
        List<AnswerResponse> answerResponseList = new ArrayList<>();

        for (Answer answer : answerList) {
             AnswerResponse answerResponse = AnswerResponse.fromAnswer(answer);
            answerResponseList.add(answerResponse);
        }
        return answerResponseList;
    }
    public static QuestionDetailResponse fromQuestion(Question question){
        return QuestionDetailResponse.builder()
                .type("question")
                .nickname(question.getNickname())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .updatedAt(question.getUpdatedAt())
                .answerResponseList(convertToAnswerResponseList(question.getAnswerList()))
                .title(question.getTitle())
                .category(question.getCategory())
                .recommend(question.getRecommend())
                .view(question.getView())
                .build();
    }
}
