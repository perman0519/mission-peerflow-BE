package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.AnswerCommentRequest;
import com.peer.missionpeerflow.service.AnswerCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/answer/comment")
public class AnswerCommentController {

    private final AnswerCommentService answerCommentService;

    @PostMapping("")
    public void postAnswerComment(@RequestBody @Valid AnswerCommentRequest request) {
        answerCommentService.postAnswerComment(request);
    }
}
