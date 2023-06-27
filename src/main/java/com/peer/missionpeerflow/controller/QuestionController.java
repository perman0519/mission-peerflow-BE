package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.DeleteQuestionRequest;
import com.peer.missionpeerflow.dto.request.QuestionRequest;
import com.peer.missionpeerflow.service.QuestionService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/question")
public class QuestionController {

	private final QuestionService questionService;

	@PostMapping("")
	public void postQuestion(@RequestBody @Valid QuestionRequest request) {
		questionService.postQuestion(request);
	}

	@PutMapping("/{questionId}")
	public void updateQuestion(@PathVariable(name = "questionId") Long questionId, @RequestBody @Valid QuestionRequest request) {
		questionService.updateQuestion(questionId, request);
	}

	@PostMapping("/{questionId}")
	public void deleteQuestion(@PathVariable(name = "questionId") Long questionId, @RequestBody @Valid DeleteQuestionRequest request) {
		questionService.deleteQuestion(questionId, request);
	}

}
