package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.comment.QuestionCommentDeleteRequest;
import com.peer.missionpeerflow.dto.request.comment.QuestionCommentModifyRequest;
import com.peer.missionpeerflow.dto.request.comment.QuestionCommentRequest;
import com.peer.missionpeerflow.dto.response.QuestionCommentResponse;
import com.peer.missionpeerflow.exception.ForbiddenException;
import com.peer.missionpeerflow.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/v1/question/comment")
@RestController
public class QuestionCommentController {

    private final QuestionCommentService questionCommentService;

    @PostMapping("")
    public String create(@Valid @RequestBody QuestionCommentRequest questionCommentRequest)
    {
        questionCommentService.create(questionCommentRequest);
        return "ok";
    }

    @GetMapping("")
    public Page<QuestionCommentResponse> getPage(@RequestParam(value = "questionId") Long questionId, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return questionCommentService.getPage(questionId, page, size);
    }

    @PutMapping("/{commentId}")
    public String modify(@Valid @RequestBody QuestionCommentModifyRequest questionCommentModifyRequest, @PathVariable("commentId") Long commentId)
    {
        if (questionCommentModifyRequest.getPassword().equals(questionCommentService.getComment(commentId).getPassword())) {
            questionCommentService.modify(commentId, questionCommentModifyRequest);
        }
        else {
            throw new ForbiddenException("비밀번호가 일치하지 않습니다.");
        }
        return "ok";
    }

    @PostMapping("/{commentId}")
    public String delete(@Valid @RequestBody QuestionCommentDeleteRequest questionCommentDeleteRequest, @PathVariable("commentId") Long commentId)
    {
        if (questionCommentDeleteRequest.getPassword().equals(questionCommentService.getComment(commentId).getPassword())) {
            questionCommentService.delete(commentId);
        }
        else {
            throw new ForbiddenException("비밀번호가 일치하지 않습니다.");
        }
        return "ok";
    }
}
