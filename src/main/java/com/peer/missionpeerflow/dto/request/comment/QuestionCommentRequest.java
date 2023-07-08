package com.peer.missionpeerflow.dto.request.comment;

import com.peer.missionpeerflow.entity.QuestionComment;
import com.peer.missionpeerflow.service.QuestionService;
import com.peer.missionpeerflow.util.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionCommentRequest {

    @NotNull(message = "제목ID를 입력해주세요.")
    private Long questionId;
    @NotBlank(message = "타입을 입력해주세요.")
    private String type;
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    @NotNull(message = "생성일 입력해주세요.")
    private LocalDateTime createdAt;

    @Builder
    public QuestionCommentRequest (LocalDateTime createdAt, Long questionId, String type, String nickname, String password, String content)
    {
        this.questionId = questionId;
        this.type = type;
        this.nickname = nickname;
        this.password = password;
        this.content = content;
        this.createdAt = createdAt;
    }
}
