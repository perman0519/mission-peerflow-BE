package com.peer.missionpeerflow.repository;

import com.peer.missionpeerflow.entity.Answer;
import com.peer.missionpeerflow.entity.AnswerComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerCommentRepository extends JpaRepository<AnswerComment, Long> {

    Page<AnswerComment> findByAnswer(Answer Answer, Pageable pageable);
}
