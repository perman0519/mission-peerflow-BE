package com.peer.missionpeerflow.repository;

import com.peer.missionpeerflow.entity.AnswerComment;
import com.peer.missionpeerflow.entity.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerCommentRepository extends JpaRepository<AnswerComment, Long> {
}
