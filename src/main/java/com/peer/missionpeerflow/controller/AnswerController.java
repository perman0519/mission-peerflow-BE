package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.answer.AnswerPasswordRequest;
import com.peer.missionpeerflow.dto.request.answer.AnswerRequest;
import com.peer.missionpeerflow.exception.UnauthorizedException;
import com.peer.missionpeerflow.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("")
    public Map<String, String> create(@Valid @RequestBody AnswerRequest answerRequest)
    {
        answerService.create(answerRequest);
        return CreateIdJson.createIdJson(Long.toString(answerRequest.getQuestionId()));
    }

    @PutMapping("/{answerId}")
    public Map<String, String> modify(@PathVariable("answerId") Long answerId, @RequestBody @Valid AnswerRequest answerRequest)
    {
        String password = answerService.getAnswer(answerId).getPassword();
        if (passwordEncoder.matches(answerRequest.getPassword(), password)) {
            answerService.modify(answerId, answerRequest);
        } else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return CreateIdJson.createIdJson(Long.toString(answerService.getAnswer(answerId).getQuestion().getQuestionId()));
    }

    @PostMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long answerId, @RequestBody @Valid AnswerPasswordRequest answerPasswordRequest)
    {
        String password = answerService.getAnswer(answerId).getPassword();
        if (passwordEncoder.matches(answerPasswordRequest.getPassword(), password)) {
            answerService.delete(answerId);
        } else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PatchMapping("/{id}")
    public void updateRecommend(@PathVariable("id") Long answerId) {
        answerService.updateRecommend(answerId);
    }

    @PostMapping("/{answerId}/adopt")
    public Map<String, String> updateAdopted(@RequestBody @Valid AnswerPasswordRequest answerPasswordRequest, @PathVariable("answerId") Long answerId) {
        String password = answerService.getAnswer(answerId).getPassword();
        if (passwordEncoder.matches(answerPasswordRequest.getPassword(), password)) {
            answerService.updateAdopted(answerId);
        } else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return CreateIdJson.createIdJson(Long.toString(answerService.getAnswer(answerId).getQuestion().getQuestionId()));
    }
}
