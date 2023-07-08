package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.request.comment.AnswerCommentModifyRequest;
import com.peer.missionpeerflow.dto.request.comment.AnswerCommentRequest;
import com.peer.missionpeerflow.dto.response.AnswerCommentResponse;
import com.peer.missionpeerflow.entity.AnswerComment;
import com.peer.missionpeerflow.repository.AnswerCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerCommentService {

    private final AnswerCommentRepository answerCommentRepository;
    private final AnswerService answerService;

    public void create(AnswerCommentRequest answerCommentRequest)
    {
        answerCommentRepository.save(toEntity(answerCommentRequest));
    }

    public AnswerComment toEntity(AnswerCommentRequest answerCommentRequest){
        return AnswerComment.builder()
                .answer(answerService.getAnswer(answerCommentRequest.getAnswerId()))
                .nickname(answerCommentRequest.getNickname())
                .password(answerCommentRequest.getPassword())
                .content(answerCommentRequest.getContent())
                .createdAt(answerCommentRequest.getCreatedAt())
                .build();
    }

    public AnswerComment getAnswerComment(Long answerCommentId){
        return answerCommentRepository.findById(answerCommentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
    }

    public Page<AnswerCommentResponse> getPage(Long answerId, int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<AnswerComment> answerCommentsPage = answerCommentRepository.findByAnswer(answerService.getAnswer(answerId), pageable);
        Page<AnswerCommentResponse> answerCommentResponsePage = answerCommentsPage.map(m -> AnswerCommentResponse.fromAnswer(m));
        return answerCommentResponsePage;
    }

    public void modify(Long commentId, AnswerCommentModifyRequest answerCommentModifyRequest)
    {
        AnswerComment answerComment = getAnswerComment(commentId);
        answerComment.setNickname(answerCommentModifyRequest.getNickname());
        answerComment.setContent(answerCommentModifyRequest.getContent());
        answerCommentRepository.save(answerComment);
    }

    public void delete(Long commentId)
    {
        answerCommentRepository.deleteById(commentId);
    }

}
