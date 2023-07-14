package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.comment.AnswerCommentRequest;
import com.peer.missionpeerflow.dto.response.AnswerCommentResponse;
import com.peer.missionpeerflow.entity.AnswerComment;
import com.peer.missionpeerflow.repository.AnswerCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnswerCommentService {

    private final AnswerCommentRepository answerCommentRepository;
    private final AnswerService answerService;

    @Transactional
    public void create(AnswerCommentRequest answerCommentRequest)
    {
        answerCommentRepository.save(toEntity(answerCommentRequest));
    }

    @Transactional
    public AnswerComment toEntity(AnswerCommentRequest answerCommentRequest){
        return AnswerComment.builder()
                .answer(answerService.getAnswer(answerCommentRequest.getAnswerId()))
                .nickname(answerCommentRequest.getNickname())
                .password(answerCommentRequest.getPassword())
                .content(answerCommentRequest.getContent())
                .build();
    }

    @Transactional
    public AnswerComment getAnswerComment(Long answerCommentId){
        return answerCommentRepository.findById(answerCommentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
    }

    @Transactional
    public Page<AnswerCommentResponse> getPage(Long answerId, int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<AnswerComment> answerCommentsPage = answerCommentRepository.findByAnswer(answerService.getAnswer(answerId), pageable);
        return answerCommentsPage.map(m -> AnswerCommentResponse.fromAnswer(m));
    }

    @Transactional
    public void modify(Long commentId, AnswerCommentRequest answerCommentRequest)
    {
        AnswerComment answerComment = getAnswerComment(commentId);
        answerComment.updateAnswerComment(answerCommentRequest.getNickname(), answerCommentRequest.getContent());
        answerCommentRepository.save(answerComment);
    }

    @Transactional
    public void delete(Long commentId)
    {
        answerCommentRepository.deleteById(commentId);
    }

}
