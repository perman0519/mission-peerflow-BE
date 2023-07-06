package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.AnswerRequest;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    public void create(AnswerRequest answerRequest){
        Answer entity = Answer.builder()
                .content(answerRequest.getContent())
                .nickname(answerRequest.getNickname())
                .password(answerRequest.getPassword())
                .question(questionService.getQuestion(answerRequest.getQuestionId()))
                .createdAt(answerRequest.getCreatedAt())
                .build();
        answerRepository.save(entity);
    }

}
