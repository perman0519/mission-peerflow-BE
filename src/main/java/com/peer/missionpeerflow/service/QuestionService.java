package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.QuestionRequest;
import com.peer.missionpeerflow.dto.response.QuestionResponse;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.util.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public QuestionResponse getQuestionResponse(Question question) {
        QuestionResponse questionResponse = QuestionResponse.fromQuestion(question);
        return questionResponse;
    }


}
