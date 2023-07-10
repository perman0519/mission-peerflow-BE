package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.answer.AnswerDeleteRequest;
import com.peer.missionpeerflow.dto.request.answer.AnswerModifyRequest;
import com.peer.missionpeerflow.dto.request.answer.AnswerRequest;
import com.peer.missionpeerflow.dto.response.QuestionDetailResponse;
import com.peer.missionpeerflow.exception.UnauthorizedException;
import com.peer.missionpeerflow.service.AnswerService;
import com.peer.missionpeerflow.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("")
    public QuestionDetailResponse create(@Valid @RequestBody AnswerRequest answerRequest)
    {
        answerService.create(answerRequest);
        return questionService.getQuestionDetailResponse(answerRequest.getQuestionId());
    }

    @PutMapping("/{id}")
    public QuestionDetailResponse modify(@PathVariable("id") Long answerId, @RequestBody @Valid AnswerModifyRequest answerModifyRequest)
    {
        if (answerModifyRequest.getPassword().equals(answerService.getAnswer(answerId).getPassword())) {
            answerService.modify(answerId, answerModifyRequest);
        } else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return questionService.getQuestionDetailResponse(answerService.getAnswer(answerId).getQuestion().getQuestionId());
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long answerId, @RequestBody @Valid AnswerDeleteRequest answerDeleteRequest)
    {
        if (answerDeleteRequest.getPassword().equals(answerService.getAnswer(answerId).getPassword())) {
            answerService.delete(answerId);
        } else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return "ok";
    }

    @PatchMapping("/{id}")
    public void updateRecommend(@PathVariable("id") Long answerId) {
        answerService.updateRecommend(answerId);
    }

    @PostMapping("/{answerId}/adopt")
    public String updateAdopted(@RequestBody @Valid AnswerDeleteRequest answerDeleteRequest, @PathVariable("answerId") Long answerId) {
        if (answerDeleteRequest.getPassword().equals(answerService.getAnswer(answerId).getPassword())) {
            answerService.updateAdopted(answerId);
        } else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return "ok";
    }
}
