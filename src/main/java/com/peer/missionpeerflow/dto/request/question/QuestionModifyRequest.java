package com.peer.missionpeerflow.dto.request.question;

import com.peer.missionpeerflow.util.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionModifyRequest {

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
    @NotNull(message = "업데이트일을 입력해주세요.")
    private LocalDateTime updatedAt;

    @Builder
    public QuestionModifyRequest(String title, String nickname, String password, String category, String content, LocalDateTime updatedAt)
    {
        this.title = title;
        this.nickname = nickname;
        this.password = password;
        this.category = Category.ofType(category);
        this.content = content;
        this.updatedAt = updatedAt;
    }
}
