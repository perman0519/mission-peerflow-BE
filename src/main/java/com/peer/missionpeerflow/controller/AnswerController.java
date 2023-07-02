package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.*;
import com.peer.missionpeerflow.service.AnswerService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/answer")
public class AnswerController {
	private final AnswerService answerService;

	@PostMapping("")
	public void postAnswer(@RequestBody @Valid PostAnswerRequest request) {
		answerService.postAnswer(request);
	}

	@PutMapping("/{answerId}")
	public void updateAnswer(@PathVariable(name = "answerId") Long answerId, @RequestBody @Valid UpdateAnswerRequest request) {
		answerService.updateAnswer(answerId, request);
	}

	@PostMapping("/{answerId}")
	public void deleteAnswer(@PathVariable(name = "answerId") Long answerId, @RequestBody @Valid DeleteRequest request) {
		answerService.deleteAnswer(answerId, request);
	}

	@PatchMapping("/{answerId}")
	public void adoptAnswer(@PathVariable(name = "answerId") Long answerId, @RequestBody @Valid AdoptRequest request) {
		answerService.adoptAnswer(answerId, request);
	}
}
