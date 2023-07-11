package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.answer.AnswerDeleteRequest;
import com.peer.missionpeerflow.dto.request.answer.AnswerModifyRequest;
import com.peer.missionpeerflow.dto.request.answer.AnswerRequest;
import com.peer.missionpeerflow.exception.UnauthorizedException;
import com.peer.missionpeerflow.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/answer")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("")
    public Map<String, String> create(@Valid @RequestBody AnswerRequest answerRequest)
    {
        answerService.create(answerRequest);
        return CreateIdJson.createIdJson(Long.toString(answerRequest.getQuestionId()));
    }

    @PutMapping("/{id}")
    public Map<String, String> modify(@PathVariable("id") Long answerId, @RequestBody @Valid AnswerModifyRequest answerModifyRequest)
    {
        if (answerModifyRequest.getPassword().equals(answerService.getAnswer(answerId).getPassword())) {
            answerService.modify(answerId, answerModifyRequest);
        } else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return CreateIdJson.createIdJson(Long.toString(answerService.getAnswer(answerId).getQuestion().getQuestionId()));
    }

    @PostMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long answerId, @RequestBody @Valid AnswerDeleteRequest answerDeleteRequest)
    {
        if (answerDeleteRequest.getPassword().equals(answerService.getAnswer(answerId).getPassword())) {
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
    public Map<String, String> updateAdopted(@RequestBody @Valid AnswerDeleteRequest answerDeleteRequest, @PathVariable("answerId") Long answerId) {
        if (answerDeleteRequest.getPassword().equals(answerService.getAnswer(answerId).getPassword())) {
            answerService.updateAdopted(answerId);
        } else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return CreateIdJson.createIdJson(Long.toString(answerService.getAnswer(answerId).getQuestion().getQuestionId()));
    }
}
