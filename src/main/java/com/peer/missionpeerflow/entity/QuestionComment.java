package com.peer.missionpeerflow.entity;

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
@Table(name = "question_comment")
@Entity
public class QuestionComment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionCommentId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

	public QuestionComment (Question question, QuestionCommentRequest request) {
		this.question = question;
		this.nickname = request.getNickname();
		this.password = request.getPassword();
		this.content = request.getContent();
		this.createdAt = LocalDateTime.now();
	}

	public void update(QuestionCommentRequest request) {
		this.content = request.getContent();
		this.updatedAt = LocalDateTime.now();
	}
}
