package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.PostAnswerRequest;
import com.peer.missionpeerflow.dto.request.UpdateAnswerRequest;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.repository.AnswerRespoitory;
import com.peer.missionpeerflow.repository.QuestionRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnswerService {

	private final AnswerRespoitory answerRespoitory;
	private final QuestionRepository questionRepository;

	@Transactional
	public void postAnswer(PostAnswerRequest request) {
		Question question = questionRepository.findByQuestionId(request.getQuestionId()).orElseThrow(() -> new RuntimeException("none"));
		question.addAnswer(new Answer(request, question));
	}

	@Transactional
	public void updateAnswer(Long answerId, UpdateAnswerRequest request) {
		Answer answer = answerRespoitory.findByAnswerId(answerId).orElseThrow(() -> new RuntimeException("none"));
		if (answer.getNickname().equals(request.getNickname()) && answer.getPassword().equals(request.getPassword())) {
			answer.update(request);
		} else {
			throw new RuntimeException(); // TODO: custom Exception로 고치기
			// 유효하지 않은 비밀번호 에러 던지기
		}
	}
}
