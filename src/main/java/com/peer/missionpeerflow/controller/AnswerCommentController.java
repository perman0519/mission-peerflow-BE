package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.comment.*;
import com.peer.missionpeerflow.dto.response.AnswerCommentResponse;
import com.peer.missionpeerflow.exception.UnauthorizedException;
import com.peer.missionpeerflow.service.AnswerCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/v1/answer/comment")
@RestController
public class AnswerCommentController {

    private final AnswerCommentService answerCommentService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("")
    public Map<String, String> create(@Valid @RequestBody AnswerCommentRequest answerCommentRequest)
    {
        answerCommentService.create(answerCommentRequest);
        return CreateIdJson.createIdJson(Long.toString(answerCommentRequest.getAnswerId()));
    }

    @GetMapping("")
    public Page<AnswerCommentResponse> getPage(@RequestParam(value = "answerId") Long answerId, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return answerCommentService.getPage(answerId, page, size);
    }

    @PutMapping("/{commentId}")
    public Map<String, String> modify (@Valid @RequestBody AnswerCommentRequest answerCommentRequest, @PathVariable("commentId") Long commentId)
    {
        String password = answerCommentService.getAnswerComment(commentId).getPassword();
        if (passwordEncoder.matches(answerCommentRequest.getPassword(), password)) {
            answerCommentService.modify(commentId, answerCommentRequest);
        }
        else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return CreateIdJson.createIdJson(Long.toString(answerCommentRequest.getAnswerId()));
    }

    @PostMapping("/{commentId}")
    public ResponseEntity delete (@Valid @RequestBody AnswerCommentPasswordRequest answerCommentPasswordRequest, @PathVariable("commentId") Long commentId)
    {
        String password = answerCommentService.getAnswerComment(commentId).getPassword();
        if (passwordEncoder.matches(answerCommentPasswordRequest.getPassword(), password)) {
        answerCommentService.delete(commentId);
        }
        else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
