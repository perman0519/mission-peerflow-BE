package com.peer.missionpeerflow.dto.request;

import com.peer.missionpeerflow.util.Category;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.Getter;

@Getter
public class QuestionRequest {

	private String type;

	@NotBlank(message = "제목을 입력해주세요.")
	private String title;

	@NotBlank(message = "닉네임을 입력해주세요.")
	private String nickname;

	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String password;

	@NotNull(message = "카테고리를 입력해주세요.")
	private String category;

	@NotNull(message = "내용을 입력해주세요.")
	private String content;

	private LocalDateTime createdAt;

	public Category getCategory() {
		return Category.ofType(category);
	}
}
