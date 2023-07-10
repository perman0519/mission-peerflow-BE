package com.peer.missionpeerflow.dto.response;

import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.util.Category;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionDetailResponse {

    private String type;
    private Category category;
    private String nickname;
    private String title;
    private String content;
    private Long recommend;
    private Long view;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<AnswerResponse> answerList;

    @Builder
    public QuestionDetailResponse (String type, String title, String content, Category category, Long recommend, Long view, String nickname, LocalDateTime createdAt, LocalDateTime updatedAt, List<AnswerResponse> answerList)
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
        this.answerList = answerList;
    }

     public static List<AnswerResponse> convertToAnswerResponseList(List<Answer> answerList) {
        List<AnswerResponse> answerResponseList = new ArrayList<>();

        for (Answer answer : answerList) {
             AnswerResponse answerResponse = AnswerResponse.fromAnswer(answer);
            answerResponseList.add(answerResponse);
        }
         Comparator<AnswerResponse> answerComparator = new Comparator<AnswerResponse>() {
             @Override
             public int compare(AnswerResponse a1, AnswerResponse a2) {
                 // 비교하고자 하는 필드에 따라서 정렬 기준을 설정합니다.
                 // 예시로, Answer 객체의 score 필드를 기준으로 내림차순 정렬합니다.
                 return Long.compare(a2.getRecommend(), a1.getRecommend());
             }
         };

         // answerList를 정렬합니다.
         Collections.sort(answerResponseList, answerComparator);
        return answerResponseList;
    }
    public static QuestionDetailResponse fromQuestion(Question question){
        return QuestionDetailResponse.builder()
                .type("question")
                .nickname(question.getNickname())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .updatedAt(question.getUpdatedAt())
                .answerList(convertToAnswerResponseList(question.getAnswerList()))
                .title(question.getTitle())
                .category(question.getCategory())
                .recommend(question.getRecommend())
                .view(question.getView())
                .build();
    }
}
