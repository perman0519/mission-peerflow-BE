package com.peer.missionpeerflow.repository;

import com.peer.missionpeerflow.dto.response.QuestionCommentResponse;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.entity.QuestionComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.stream.events.Comment;
import java.util.Optional;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {

    Page<QuestionComment> findByQuestionQuestionId(long questionId, Pageable pageable);
}
