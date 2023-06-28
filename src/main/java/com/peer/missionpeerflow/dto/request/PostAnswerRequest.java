package com.peer.missionpeerflow.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class PostAnswerRequest {

	private String type;

	@JsonProperty("question_id")
	@NotNull(message = "question id를 입력해주세요.")
	private Long questionId;

	@NotBlank(message = "닉네임을 입력해주세요.")
	private String nickname;

	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String password;

	@NotNull(message = "내용을 입력해주세요.")
	private String content;

	private LocalDateTime created;
}
