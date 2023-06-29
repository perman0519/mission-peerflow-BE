package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.DeleteRequest;
import com.peer.missionpeerflow.dto.request.QuestionRequest;
import com.peer.missionpeerflow.dto.response.QuestionResponse;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.service.QuestionService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/question")
public class QuestionController {

	private final QuestionService questionService;

	@GetMapping("/{questionId}")
	@ResponseBody
	public QuestionResponse getQuestion(@PathVariable(name = "questionId") Long questionId) {
		return questionService.getQuestion(questionId);
	}

	@PostMapping("")
	public void postQuestion(@RequestBody @Valid QuestionRequest request) {
		questionService.postQuestion(request);
	}

	@PutMapping("/{questionId}")
	public void updateQuestion(@PathVariable(name = "questionId") Long questionId, @RequestBody @Valid QuestionRequest request) {
		questionService.updateQuestion(questionId, request);
	}

	@PostMapping("/{questionId}")
	public void deleteQuestion(@PathVariable(name = "questionId") Long questionId, @RequestBody @Valid DeleteRequest request) {
		questionService.deleteQuestion(questionId, request);
	}

}
