package com.peer.missionpeerflow.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AdoptRequest {
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
