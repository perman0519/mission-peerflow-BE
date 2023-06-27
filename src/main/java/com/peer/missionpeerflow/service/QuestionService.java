package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.PostQuestionRequest;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;

	@Transactional
	public void postQuestion(PostQuestionRequest request) {
		Question question = new Question(request);
		questionRepository.save(question);
	}
}
