package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.question.QuestionModifyRequest;
import com.peer.missionpeerflow.dto.request.question.QuestionRequest;
import com.peer.missionpeerflow.dto.response.QuestionDetailResponse;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.exception.NotFoundException;
import com.peer.missionpeerflow.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public Long create(@NotNull QuestionRequest questionRequest) {
        Question entity = Question.builder()
                .title(questionRequest.getTitle())
                .content(questionRequest.getContent())
                .nickname(questionRequest.getNickname())
                .password(questionRequest.getPassword())
                .category(questionRequest.getCategory())
                .view(0L)
                .build();
        this.questionRepository.save(entity);
        return entity.getQuestionId();
    }

    @Transactional
    public void modify(@NotNull QuestionModifyRequest questionModifyRequest, Long questionId) {
        Question entity = getQuestion(questionId);
        entity.update(questionModifyRequest.getTitle(), questionModifyRequest.getNickname(), questionModifyRequest.getCategory(), questionModifyRequest.getContent());
        questionRepository.save(entity);
    }

    @Transactional
    public void delete(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    @Transactional
    public QuestionDetailResponse getQuestionDetailResponse(Long questionId) {
        QuestionDetailResponse questionDetailResponse = QuestionDetailResponse.fromQuestion(getQuestion(questionId));
        return questionDetailResponse;
    }

    @Transactional
    public void updateView(Long questionId) {
        Question entity = getQuestion(questionId);
        entity.updateView(entity.getView() + 1);
        questionRepository.save(entity);
    }

    @Transactional
    public Question getQuestion(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("해당 게시글이 존재하지 않습니다."));
    }

    @Transactional
    public void updateRecommend(Long questionId) {
        Question entity = getQuestion(questionId);
        entity.updateRecommend(entity.getRecommend() + 1);
        questionRepository.save(entity);
    }
}
