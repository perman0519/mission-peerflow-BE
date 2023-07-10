package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.question.QuestionDeleteRequest;
import com.peer.missionpeerflow.dto.request.question.QuestionModifyRequest;
import com.peer.missionpeerflow.dto.request.question.QuestionRequest;
import com.peer.missionpeerflow.dto.response.QuestionDetailResponse;
import com.peer.missionpeerflow.exception.UnauthorizedException;
import com.peer.missionpeerflow.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/question")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("")
    public String create(@Valid @RequestBody QuestionRequest questionRequest){
        questionService.create(questionRequest);
        return "ok";
    }

    @GetMapping("/{id}")
    public QuestionDetailResponse detail (@PathVariable("id") Long questionId) {
        QuestionDetailResponse questionResponse = questionService.getQuestionDetailResponse(questionId);
        questionService.updateView(questionId);
        return questionResponse;
    }

    @PutMapping("/{id}")
    public QuestionDetailResponse modify (@Valid @RequestBody QuestionModifyRequest questionModifyRequest, @PathVariable("id") Long questionId) {
        String questionPassword = questionService.getQuestion(questionId).getPassword();
        if (questionModifyRequest.getPassword().equals(questionPassword)) {
            questionService.modify(questionModifyRequest, questionId);
        }
        else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return questionService.getQuestionDetailResponse(questionId);
    }

    @PostMapping("/{id}")
    public String delete (@RequestBody @Valid QuestionDeleteRequest questionDeleteRequest, @PathVariable("id") Long questionId) {
        String questionPassword = questionService.getQuestion(questionId).getPassword();
        if (questionDeleteRequest.getPassword().equals(questionPassword)) {
            questionService.delete(questionId);
        }
        else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return "ok";
    }

    @PatchMapping("/{id}")
    public void updateRecommend(@PathVariable("id") Long questionId) {
        questionService.updateRecommend(questionId);
    }
}
