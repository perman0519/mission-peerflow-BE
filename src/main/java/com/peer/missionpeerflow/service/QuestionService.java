package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.QuestionRequest;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.repository.QuestionRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;

	@Transactional
	public void postQuestion(QuestionRequest request) {
		Question question = new Question(request);
		questionRepository.save(question);
	}

	@Transactional
	public void updateQuestion(Long questionId, QuestionRequest request) {
		Question question = questionRepository.findByQuestionId(questionId).orElseThrow(() -> new RuntimeException("none"));
		// TODO: custom Exception로 고치기 question이 존재하지 않는 다는 에러

		if ((question.getNickname().equals(request.getNickname())) && question.getPassword().equals(request.getPassword())) {
			question.update(request);
		} else {
			throw new RuntimeException(); // TODO: custom Exception로 고치기
			// 유효하지 않은 비밀번호 에러 던지기
		}
	}
}
