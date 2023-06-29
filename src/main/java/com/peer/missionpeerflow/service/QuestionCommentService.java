package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.QuestionCommentRequest;
import com.peer.missionpeerflow.dto.response.QuestionCommentResponse;
import com.peer.missionpeerflow.dto.response.QuestionResponse;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.entity.QuestionComment;
import com.peer.missionpeerflow.exception.ForbiddenException;
import com.peer.missionpeerflow.exception.NotFoundException;
import com.peer.missionpeerflow.repository.QuestionCommentRepository;
import com.peer.missionpeerflow.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionCommentService {

    private final QuestionRepository questionRepository;
    private final QuestionCommentRepository questionCommentRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public Page<QuestionCommentResponse> getQuestionComment(long questionId, Pageable pageable) {
        Page<QuestionComment> comments = questionCommentRepository.findByQuestionQuestionId(questionId, pageable);
        return comments.map(q -> modelMapper.map(q, QuestionCommentResponse.class));
    }

    @Transactional
    public void postQuestionComment(QuestionCommentRequest request) {
        Question question = questionRepository.findById(request.getQuestionId()).
                orElseThrow(() -> new NotFoundException("해당 Id의 질문이 존재하지 않습니다."));
        QuestionComment comment = new QuestionComment(question, request);
        questionCommentRepository.save(comment);
    }

    @Transactional
    public void updateQuestionComment(long commentId, QuestionCommentRequest request) {
        QuestionComment comment = questionCommentRepository.findById(commentId).
                orElseThrow(() -> new NotFoundException("해당 Id의 댓글이 존재하지 않습니다."));

        if ((comment.getNickname().equals(request.getNickname())) && comment.getPassword().equals(request.getPassword())) {
            comment.update(request);
        } else {
            throw new ForbiddenException("유효하지 않은 비밀번호입니다.");
            // 유효하지 않은 비밀번호 에러 던지기
        }
    }

    @Transactional
    public void deleteQuestionComment(long commentId, QuestionCommentRequest request) {
        QuestionComment comment = questionCommentRepository.findById(commentId).
                orElseThrow(() -> new NotFoundException("해당 Id의 댓글이 존재하지 않습니다."));

        if ((comment.getNickname().equals(request.getNickname())) && comment.getPassword().equals(request.getPassword())) {
           questionCommentRepository.delete(comment);
        } else {
            throw new ForbiddenException("유효하지 않은 비밀번호입니다.");
            // 유효하지 않은 비밀번호 에러 던지기
        }
    }
}
