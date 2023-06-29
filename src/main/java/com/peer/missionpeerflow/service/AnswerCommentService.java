package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.AnswerCommentRequest;
import com.peer.missionpeerflow.dto.request.QuestionCommentRequest;
import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.AnswerComment;
import com.peer.missionpeerflow.entity.QuestionComment;
import com.peer.missionpeerflow.exception.ForbiddenException;
import com.peer.missionpeerflow.exception.NotFoundException;
import com.peer.missionpeerflow.repository.AnswerCommentRepository;
import com.peer.missionpeerflow.repository.AnswerRespoitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AnswerCommentService {

    private final AnswerRespoitory answerRespoitory;
    private final AnswerCommentRepository answerCommentRepository;

    @Transactional
    public void postAnswerComment(AnswerCommentRequest request) {
        Answer answer = answerRespoitory.findByAnswerId(request.getAnswerId()).
                orElseThrow(() -> new NotFoundException("해당 Id의 질문이 존재하지 않습니다."));
        AnswerComment comment = new AnswerComment(answer, request);
        answerCommentRepository.save(comment);
    }

    @Transactional
    public void updateAnswerComment(long commentId, AnswerCommentRequest request) {
        AnswerComment comment = answerCommentRepository.findByAnswerCommentId(commentId).
                orElseThrow(() -> new NotFoundException("해당 Id의 댓글이 존재하지 않습니다."));

        if ((comment.getNickname().equals(request.getNickname())) && comment.getPassword().equals(request.getPassword())) {
            comment.update(request);
        } else {
            throw new ForbiddenException("유효하지 않은 비밀번호입니다.");
            // 유효하지 않은 비밀번호 에러 던지기
        }
    }

    @Transactional
    public void deleteAnswerComment(long commentId, AnswerCommentRequest request) {
        AnswerComment comment = answerCommentRepository.findByAnswerCommentId(commentId).
                orElseThrow(() -> new NotFoundException("해당 Id의 댓글이 존재하지 않습니다."));

        if ((comment.getNickname().equals(request.getNickname())) && comment.getPassword().equals(request.getPassword())) {
            answerCommentRepository.delete(comment);
        } else {
            throw new ForbiddenException("유효하지 않은 비밀번호입니다.");
            // 유효하지 않은 비밀번호 에러 던지기
        }
    }
}
