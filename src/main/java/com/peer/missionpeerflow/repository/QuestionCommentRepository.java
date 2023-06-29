package com.peer.missionpeerflow.repository;

import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.entity.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.stream.events.Comment;
import java.util.Optional;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {

    Optional<QuestionComment> findByQuestionCommentId(Long commentId);
}
