package com.peer.missionpeerflow.dto.response;

import com.peer.missionpeerflow.dto.request.QuestionRequest;
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

    private long questionId;
    private String title;
    private int answerCount;
    private Category category;
    private long recommend;
    private long view;
    private String nickname;
    private LocalDateTime createdAt;
    private String content;
    private List<Answer> answerList;
    private List<QuestionComment> questionCommentList;

    @Builder
    public QuestionResponse (Question question){
        this.questionId = question.getQuestionId();
        this.title = question.getTitle();
        this.answerCount = question.getAnswerList().size();
        this.category = question.getCategory();
        this.recommend = question.getRecommend();
        this.view = question.getView();
        this.nickname = question.getNickname();
        this.createdAt = question.getCreatedAt();
        this.content = question.getContent();
        this.answerList = getAnswerList();
        this.questionCommentList = getQuestionCommentList();
    }

}
