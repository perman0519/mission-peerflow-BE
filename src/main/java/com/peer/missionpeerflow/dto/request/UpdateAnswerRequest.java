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

	@NotNull(message = "question id를 입력해주세요.")
	private Long questionId;

	@NotBlank(message = "닉네임을 입력해주세요.")
	private String nickname;

	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String password;

	@NotNull(message = "내용을 입력해주세요.")
	private String content;

	private LocalDateTime updatedAt;
}
