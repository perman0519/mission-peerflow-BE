package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.QuestionRequest;
import com.peer.missionpeerflow.dto.response.QuestionDetailResponse;
import com.peer.missionpeerflow.dto.response.QuestionResponse;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void create(QuestionRequest questionRequest) {
        Question entity = Question.builder()
                .title(questionRequest.getTitle())
                .content(questionRequest.getContent())
                .nickname(questionRequest.getNickname())
                .password(questionRequest.getPassword())
                .createdAt(questionRequest.getCreatedAt())
                .category(questionRequest.getCategory())
                .build();
        questionRepository.save(entity);
    }

    public QuestionDetailResponse getQuestionDetailResponse(Long questionId) {
        QuestionDetailResponse questionDetailResponse = QuestionDetailResponse.fromQuestion(questionRepository.findById(questionId).get());
        return questionDetailResponse;
    }

    public QuestionResponse getQuestionResponse(Long questionId) {
        QuestionResponse questionResponse = QuestionResponse.fromQuestion(questionRepository.findById(questionId).get());
        return questionResponse;
    }

    public Question getQuestion(Long questionId) {
        return questionRepository.findById(questionId).get();
    }
}
