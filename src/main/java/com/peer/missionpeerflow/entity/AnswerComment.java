package com.peer.missionpeerflow.entity;

import com.peer.missionpeerflow.dto.request.AnswerCommentRequest;
import com.peer.missionpeerflow.dto.request.QuestionCommentRequest;
import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "answer_comment")
@Entity
public class AnswerComment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long answerCommentId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "answer_id")
	private Answer answer;

	public AnswerComment (Answer answer, AnswerCommentRequest request) {
		this.answer = answer;
		this.nickname = request.getNickname();
		this.password = request.getPassword();
		this.content = request.getContent();
		this.createdAt = LocalDateTime.now();
	}

	public void update(AnswerCommentRequest request) {
		this.content = request.getContent();
		this.updatedAt = LocalDateTime.now();
	}
}
