package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.PostAnswerRequest;
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
}
