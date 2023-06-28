package com.peer.missionpeerflow.repository;

import com.peer.missionpeerflow.entity.Answer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRespoitory extends JpaRepository<Answer, Long> {

	Optional<Answer> findByAnswerId(Long answerId);
}
