package com.peer.missionpeerflow.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionDeleteRequest {

    @NotBlank
    private String password;


    @Builder
    public QuestionDeleteRequest(String password)
    {
        this.password = password;
    }



}
