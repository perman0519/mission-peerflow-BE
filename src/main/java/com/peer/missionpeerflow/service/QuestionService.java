package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.QuestionModifyRequest;
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
                .view(0L)
                .build();
        this.questionRepository.save(entity);
    }

    public void modify(QuestionModifyRequest questionModifyRequest, Long questionId) {
        Question entity = questionRepository.findById(questionId).get();
        entity.update(questionModifyRequest.getTitle(), questionModifyRequest.getNickname(), questionModifyRequest.getCategory(), questionModifyRequest.getContent(), questionModifyRequest.getUpdatedAt());
        questionRepository.save(entity);
    }

    public void delete(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    public QuestionDetailResponse getQuestionDetailResponse(Long questionId) {
        QuestionDetailResponse questionDetailResponse = QuestionDetailResponse.fromQuestion(questionRepository.findById(questionId).get());
        return questionDetailResponse;
    }

    public void updateView(Long questionId) {
        Question entity = questionRepository.findById(questionId).get();
        entity.updateView(entity.getView() + 1);
        questionRepository.save(entity);
    }

    public QuestionResponse getQuestionResponse(Long questionId) {
        QuestionResponse questionResponse = QuestionResponse.fromQuestion(questionRepository.findById(questionId).get());
        return questionResponse;
    }

    public Question getQuestion(Long questionId) {
        return questionRepository.findById(questionId).get();
    }
}
