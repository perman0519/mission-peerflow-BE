package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.DeleteRequest;
import com.peer.missionpeerflow.dto.request.PostAnswerRequest;
import com.peer.missionpeerflow.dto.request.QuestionRequest;
import com.peer.missionpeerflow.dto.request.UpdateAnswerRequest;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.service.AnswerService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PutMapping("/{answerId}")
	public void updateAnswer(@PathVariable(name = "answerId") Long answerId, @RequestBody @Valid UpdateAnswerRequest request) {
		answerService.updateAnswer(answerId, request);
	}

	@PostMapping("/{answerId}")
	public void deleteAnswer(@PathVariable(name = "answerId") Long answerId, @RequestBody @Valid DeleteRequest request) {
		answerService.deleteAnswer(answerId, request);
	}

	@PostMapping("/{answerId}/recommend")
	public String recommendAnswer(@PathVariable Long answerId) {
		answerService.recommendAnswer(answerId);
		return "redirect:/v1/answer" + answerId; // 해당 글로 리다이렉트
	}


}
