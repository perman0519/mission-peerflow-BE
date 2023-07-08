package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.comment.*;
import com.peer.missionpeerflow.dto.response.AnswerCommentResponse;
import com.peer.missionpeerflow.exception.ForbiddenException;
import com.peer.missionpeerflow.service.AnswerCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/v1/answer/comment")
@RestController
public class AnswerCommentController {

    private final AnswerCommentService answerCommentService;

    @PostMapping("")
    public String create(@Valid @RequestBody AnswerCommentRequest answerCommentRequest)
    {
        answerCommentService.create(answerCommentRequest);
        return "ok";
    }

    @GetMapping("")
    public Page<AnswerCommentResponse> getPage(@RequestParam(value = "answerId") Long answerId, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return answerCommentService.getPage(answerId, page, size);
    }

    @PutMapping("/{commentId}")
    public String modify (@Valid @RequestBody AnswerCommentModifyRequest answerCommentModifyRequest, @PathVariable("commentId") Long commentId)
    {
        if (answerCommentModifyRequest.getPassword().equals(answerCommentService.getAnswerComment(commentId).getPassword())) {
            answerCommentService.modify(commentId, answerCommentModifyRequest);
        }
        else {
            throw new ForbiddenException("비밀번호가 일치하지 않습니다.");
        }
        return "ok";
    }

    @PostMapping("/{commentId}")
    public String delete (@Valid @RequestBody AnswerCommentDeleteRequest answerCommentDeleteRequest, @PathVariable("commentId") Long commentId)
    {
        if (answerCommentDeleteRequest.getPassword().equals(answerCommentService.getAnswerComment(commentId).getPassword())) {
            answerCommentService.delete(commentId);
        }
        else {
            throw new ForbiddenException("비밀번호가 일치하지 않습니다.");
        }
        return "ok";
    }
}
