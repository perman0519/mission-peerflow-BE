package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.*;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.exception.ForbiddenException;
import com.peer.missionpeerflow.exception.NotFoundException;
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
		Question question = questionRepository.findByQuestionId(request.getQuestionId()).orElseThrow(() -> new NotFoundException("해당 Id의 질문이 존재하지 않습니다."));
		question.addAnswer(new Answer(request, question));
	}

	@Transactional
	public void updateAnswer(Long answerId, UpdateAnswerRequest request) {
		Answer answer = answerRespoitory.findByAnswerId(answerId).orElseThrow(() -> new NotFoundException("해당 Id의 답글이 존재하지 않습니다."));
		if (answer.getNickname().equals(request.getNickname()) && answer.getPassword().equals(request.getPassword())) {
			answer.update(request);
		} else {
			throw new ForbiddenException("유효하지 않은 비밀번호입니다.");
		}
	}

	@Transactional
	public void deleteAnswer(Long answerId, DeleteRequest request) {
		Answer answer = answerRespoitory.findByAnswerId(answerId).orElseThrow(() -> new NotFoundException("해당 Id의 답글이 존재하지 않습니다."));
		if (answer.getPassword().equals(request.getPassword())) {
			answerRespoitory.delete(answer);
		} else {
			throw new ForbiddenException("유효하지 않은 비밀번호입니다.");
		}
	}

	@Transactional
	public void adoptAnswer(Long answerId, AdoptRequest request) {
		Answer answer = answerRespoitory.findByAnswerId(answerId).orElseThrow(() -> new NotFoundException("해당 Id의 답글이 존재하지 않습니다."));
		if (answer.getQuestion().getPassword().equals(request.getPassword())) {
			answer.setIsAdopted(true);
		} else {
			throw new ForbiddenException("유효하지 않은 비밀번호입니다.");
		}
	}

	public void recommendAnswer(Long answerId){
		Answer answer = answerRespoitory.findById(answerId).orElseThrow(() -> {
			return new IllegalArgumentException("답변이 존재하지 않습니다");
		});
		answer.setRecommend(answer.getRecommend() + 1);
		answerRespoitory.save(answer);
	}
}
