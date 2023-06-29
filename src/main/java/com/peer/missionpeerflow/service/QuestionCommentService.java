package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.QuestionCommentRequest;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.entity.QuestionComment;
import com.peer.missionpeerflow.exception.NotFoundException;
import com.peer.missionpeerflow.repository.QuestionCommentRepository;
import com.peer.missionpeerflow.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QuestionCommentService {

    private final QuestionRepository questionRepository;
    private final QuestionCommentRepository questionCommentRepository;

    @Transactional
    public void postQuestionComment(long questionId, QuestionCommentRequest request) {
        Question question =  questionRepository.findByQuestionId(questionId).
                orElseThrow(() -> new NotFoundException("해당 Id의 질문이 존재하지 않습니다."));
        QuestionComment comment = new QuestionComment(question, request);
        questionCommentRepository.save(comment);
    }
}
