package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.QuestionRequest;
import com.peer.missionpeerflow.dto.response.QuestionDetailResponse;
import com.peer.missionpeerflow.dto.response.QuestionResponse;
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
    public String create(@RequestBody QuestionRequest questionRequest){
        questionService.create(questionRequest);
        return "ok";
    }

    @GetMapping("/{id}")
    public QuestionDetailResponse detail (@PathVariable("id") Long questionId) {
        QuestionDetailResponse questionResponse = questionService.getQuestionDetailResponse(questionId);
        return questionResponse;
    }

    @PutMapping("/{id}")
    public QuestionDetailResponse modify (@PathVariable("id") Long questionId) {
        QuestionDetailResponse questionResponse = questionService.getQuestionDetailResponse(questionId);
        return questionResponse;
    }
}
