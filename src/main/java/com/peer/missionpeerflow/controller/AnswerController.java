package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.answer.AnswerDeleteRequest;
import com.peer.missionpeerflow.dto.request.answer.AnswerModifyRequest;
import com.peer.missionpeerflow.dto.request.answer.AnswerRequest;
import com.peer.missionpeerflow.dto.response.QuestionDetailResponse;
import com.peer.missionpeerflow.exception.ForbiddenException;
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
            throw new ForbiddenException("비밀번호가 일치하지 않습니다.");
        }
        return questionService.getQuestionDetailResponse(answerModifyRequest.getQuestionId());
    }

    @PostMapping("/{id}")
    public QuestionDetailResponse delete(@PathVariable("id") Long answerId, @RequestBody @Valid AnswerDeleteRequest answerDeleteRequest)
    {
        Long questionId = answerService.getAnswer(answerId).getQuestion().getQuestionId();
        if (answerDeleteRequest.getPassword().equals(answerService.getAnswer(answerId).getPassword())) {
            answerService.delete(answerId);
        } else {
            throw new ForbiddenException("비밀번호가 일치하지 않습니다.");
        }
        return questionService.getQuestionDetailResponse(questionId);
    }
}
