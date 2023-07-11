package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.question.QuestionDeleteRequest;
import com.peer.missionpeerflow.dto.request.question.QuestionModifyRequest;
import com.peer.missionpeerflow.dto.request.question.QuestionRequest;
import com.peer.missionpeerflow.dto.response.QuestionDetailResponse;
import com.peer.missionpeerflow.exception.UnauthorizedException;
import com.peer.missionpeerflow.service.QuestionService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/question")
public class QuestionController {

    private final QuestionService questionService;


    @GetMapping("/{id}")
    public QuestionDetailResponse detail (@PathVariable("id") Long questionId) {
        QuestionDetailResponse questionResponse = questionService.getQuestionDetailResponse(questionId);
        questionService.updateView(questionId);

        return questionResponse;
    }

    @PostMapping("")
    public Map<String, String> create(@Valid @RequestBody QuestionRequest questionRequest){
        Long questionId = questionService.create(questionRequest);
        return CreateIdJson.createIdJson(Long.toString(questionId));
    }

    @PutMapping("/{id}")
    public Map<String, String> modify (@Valid @RequestBody QuestionModifyRequest questionModifyRequest, @PathVariable("id") Long questionId) {
        String questionPassword = questionService.getQuestion(questionId).getPassword();
        if (questionModifyRequest.getPassword().equals(questionPassword)) {
            questionService.modify(questionModifyRequest, questionId);
        }
        else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return CreateIdJson.createIdJson(Long.toString(questionId));
    }

    @PostMapping("/{id}")
    public ResponseEntity delete (@RequestBody @Valid QuestionDeleteRequest questionDeleteRequest, @PathVariable("id") Long questionId) {
        String questionPassword = questionService.getQuestion(questionId).getPassword();
        if (questionDeleteRequest.getPassword().equals(questionPassword)) {
            questionService.delete(questionId);
        }
        else {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PatchMapping("/{id}")
    public void updateRecommend(@PathVariable("id") Long questionId) {
        questionService.updateRecommend(questionId);
    }
}
