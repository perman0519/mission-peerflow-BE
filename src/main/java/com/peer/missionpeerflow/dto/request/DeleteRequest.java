package com.peer.missionpeerflow.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteRequest {

	private String type;

	@NotNull(message = "비밀번호를 입력해주세요.")
	private String password;
}
