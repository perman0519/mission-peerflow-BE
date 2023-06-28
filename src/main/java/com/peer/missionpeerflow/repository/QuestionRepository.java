package com.peer.missionpeerflow.repository;

import com.peer.missionpeerflow.entity.Question;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	Optional<Question> findByQuestionId(Long questionId);
}
