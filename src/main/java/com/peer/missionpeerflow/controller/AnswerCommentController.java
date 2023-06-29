package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.AnswerCommentRequest;
import com.peer.missionpeerflow.dto.request.QuestionCommentRequest;
import com.peer.missionpeerflow.service.AnswerCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{commentId}")
    public void updateAnswerComment(@PathVariable(name = "commentId") Long commentId, @RequestBody @Valid AnswerCommentRequest request) {
        answerCommentService.updateAnswerComment(commentId, request);
    }

    @PostMapping("/{commentId}")
    public void deleteAnswerComment(@PathVariable(name = "commentId") Long commentId, @RequestBody @Valid AnswerCommentRequest request) {
        answerCommentService.deleteAnswerComment(commentId, request);
    }
}
