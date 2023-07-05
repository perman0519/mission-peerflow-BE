package com.peer.missionpeerflow.dto.request;

import com.peer.missionpeerflow.util.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
    @NotBlank
    private Category category;
    @NotBlank
    private String content;

    private LocalDateTime createdAt;

    @Builder
    public QuestionRequest(String title, String nickname, String password,  String content, String category)
    {
        this.title = title;
        this.nickname = nickname;
        this.password = password;
        this.category = Category.ofType(category);
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}
