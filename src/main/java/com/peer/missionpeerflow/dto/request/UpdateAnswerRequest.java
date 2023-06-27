package com.peer.missionpeerflow.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.Getter;

@Getter
public class UpdateAnswerRequest {

	private String type;

	@JsonProperty("question_id")
	@NotBlank
	private Long questionId;

	@NotBlank
	private String nickname;

	@NotBlank
	private String password;

	@NotNull
	private String content;

	@PastOrPresent
	private LocalDateTime updated;
}
