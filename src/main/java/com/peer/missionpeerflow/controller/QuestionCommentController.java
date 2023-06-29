package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.QuestionCommentRequest;
import com.peer.missionpeerflow.dto.request.QuestionRequest;
import com.peer.missionpeerflow.service.QuestionCommentService;
import com.peer.missionpeerflow.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/question/comment")
public class QuestionCommentController {

    private final QuestionCommentService questionCommentService;

    @PostMapping("")
    public void postQuestionComment(@RequestBody @Valid QuestionCommentRequest request) {
        questionCommentService.postQuestionComment(request);
    }

    @PutMapping("/{commentId}")
    public void updateQuestionComment(@PathVariable(name = "commentId") Long commentId, @RequestBody @Valid QuestionCommentRequest request) {
        questionCommentService.updateQuestionComment(commentId, request);
    }

    @PostMapping("/{commentId}")
    public void deleteQuestionComment(@PathVariable(name = "commentId") Long commentId, @RequestBody @Valid QuestionCommentRequest request) {
        questionCommentService.deleteQuestionComment(commentId, request);
    }
}
