package com.peer.missionpeerflow.dto.request;

import com.peer.missionpeerflow.util.Category;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.Getter;

@Getter
public class PostQuestionRequest {

	@NotBlank()
	private String type;

	@NotBlank()
	private String title;

	@NotBlank()
	private String nickname;

	@NotBlank()
	private String password;

	@NotNull()
	private String category;

	@NotNull
	private String content;

	@PastOrPresent()
	private LocalDate created;

	public Category getCategory() {
		return Category.ofType(category);
	}
}
