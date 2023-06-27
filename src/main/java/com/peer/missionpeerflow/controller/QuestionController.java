package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.PostQuestionRequest;
import com.peer.missionpeerflow.service.QuestionService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/question")
public class QuestionController {

	private final QuestionService questionService;

	@PostMapping("")
	public void postQuestion(@RequestBody @Valid PostQuestionRequest request) {
		questionService.postQuestion(request);
	}


}
