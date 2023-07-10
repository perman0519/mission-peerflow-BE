package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.answer.AnswerModifyRequest;
import com.peer.missionpeerflow.dto.request.answer.AnswerRequest;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.exception.ForbiddenException;
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
                .recommend(0L)
                .build();
        answerRepository.save(entity);
    }

    public void modify(Long answerId, AnswerModifyRequest answerModifyRequest){
        Answer answer = getAnswer(answerId);
        answer.update(answerModifyRequest.getContent(), answerModifyRequest.getNickname(), answerModifyRequest.getUpdatedAt());
        answerRepository.save(answer);
    }

    public Answer getAnswer(Long answerId){
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ForbiddenException("해당 답변이 없습니다."));
        return answer;
    }

    public void delete(Long answerId){
        answerRepository.deleteById(answerId);
    }

    public void updateRecommend(Long answerId){
        Answer answer = getAnswer(answerId);
        answer.updateRecommend(answer.getRecommend() + 1);
        answerRepository.save(answer);
    }

}
