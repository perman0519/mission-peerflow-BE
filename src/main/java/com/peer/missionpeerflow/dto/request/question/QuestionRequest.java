package com.peer.missionpeerflow.dto.request.question;

import com.peer.missionpeerflow.util.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotNull(message = "카테고리를 선택해주세요.")
    private Category category;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Builder
    public QuestionRequest(String title, String nickname, String password, String category, String content)
    {
        this.title = title;
        this.nickname = nickname;
        this.password = password;
        this.category = Category.ofType(category);
        this.content = content;
    }
}
