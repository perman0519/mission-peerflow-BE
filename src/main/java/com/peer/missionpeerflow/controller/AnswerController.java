package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.PostAnswerRequest;
import com.peer.missionpeerflow.service.AnswerService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/answer")
public class AnswerController {
	private final AnswerService answerService;

	@PostMapping("")
	public void postAnswer(@RequestBody @Valid PostAnswerRequest request) {
		answerService.postAnswer(request);
	}
}
