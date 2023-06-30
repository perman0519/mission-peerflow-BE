package com.peer.missionpeerflow.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseResponse {
    protected String nickname;
    protected String content;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
