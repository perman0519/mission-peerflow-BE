package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.DeleteRequest;
import com.peer.missionpeerflow.dto.request.QuestionRequest;
import com.peer.missionpeerflow.dto.response.AnswerResponse;
import com.peer.missionpeerflow.dto.response.QuestionResponse;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.exception.ForbiddenException;
import com.peer.missionpeerflow.exception.NotFoundException;
import com.peer.missionpeerflow.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final ModelMapper modelMapper = new ModelMapper();
	private QuestionResponse of(Question question) {
		return modelMapper.map(question, QuestionResponse.class);
	}

	@Transactional
	public QuestionResponse getQuestion(long questionId) {
		Question question = questionRepository.findById(questionId).
				orElseThrow(() -> new NotFoundException("해당 Id의 질문이 존재하지 않습니다."));
		question.setView(question.getView() + 1);
		questionRepository.save(question);
		return of(question);
	}

	@Transactional
	public void postQuestion(QuestionRequest request) {
		Question question = new Question(request);
		questionRepository.save(question);
	}

	@Transactional
	public void updateQuestion(Long questionId, QuestionRequest request) {
		Question question = questionRepository.findByQuestionId(questionId).orElseThrow(() -> new NotFoundException("해당 Id의 질문이 존재하지 않습니다."));

		if ((question.getNickname().equals(request.getNickname())) && question.getPassword().equals(request.getPassword())) {
			question.update(request);
		} else {
			throw new ForbiddenException("유효하지 않은 비밀번호입니다.");
			// 유효하지 않은 비밀번호 에러 던지기
		}
	}

	@Transactional
	public void deleteQuestion(Long questionId, DeleteRequest request) {
		Question question = questionRepository.findByQuestionId(questionId).orElseThrow(() -> new NotFoundException("해당 Id의 질문이 존재하지 않습니다."));
		if (question.getPassword().equals(request.getPassword())) {
			questionRepository.delete(question);
		} else {
			throw new ForbiddenException("유효하지 않은 비밀번호입니다.");
			// 유효하지 않은 비밀번호 에러 던지기
		}
	}

	public void recommendQuestion(Long questionId) {
		Question question = questionRepository.findById(questionId).orElseThrow(() -> {
			return new IllegalArgumentException("질문이 존재하지 않습니다");
		});
		question.setRecommend(question.getRecommend() + 1);
		questionRepository.save(question);
	}
}
