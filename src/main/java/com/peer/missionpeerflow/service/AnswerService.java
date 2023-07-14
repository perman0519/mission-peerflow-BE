package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.answer.AnswerRequest;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.exception.ForbiddenException;
import com.peer.missionpeerflow.exception.NotFoundException;
import com.peer.missionpeerflow.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    @Transactional
    public void create(AnswerRequest answerRequest){
        Answer entity = Answer.builder()
                .content(answerRequest.getContent())
                .nickname(answerRequest.getNickname())
                .password(answerRequest.getPassword())
                .question(questionService.getQuestion(answerRequest.getQuestionId()))
                .recommend(0L)
                .build();
        answerRepository.save(entity);
    }

    @Transactional
    public void modify(Long answerId, AnswerRequest answerRequest){
        Answer answer = getAnswer(answerId);
        answer.update(answerRequest.getContent(), answerRequest.getNickname());
        answerRepository.save(answer);
    }

    @Transactional
    public Answer getAnswer(Long answerId){
        return answerRepository.findById(answerId).orElseThrow(() -> new NotFoundException("해당 답변이 없습니다."));
    }

    @Transactional
    public void delete(Long answerId){
        answerRepository.deleteById(answerId);
    }

    @Transactional
    public void updateRecommend(Long answerId){
        Answer answer = getAnswer(answerId);
        answer.updateRecommend(answer.getRecommend() + 1);
        answerRepository.save(answer);
    }

    @Transactional
    public void updateAdopted(Long answerId){
        Answer answer = getAnswer(answerId);
        Question question = answer.getQuestion();
        for (Answer a : question.getAnswerList()) {
            if (a.getIsAdopted()) {
                throw new ForbiddenException("이미 채택된 답변이 있습니다.");
            }
        }
        answer.updateIsAdopted(true);
        answerRepository.save(answer);
    }
}
