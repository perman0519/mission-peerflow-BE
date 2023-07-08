package com.peer.missionpeerflow.entity;

import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
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

	@Builder
	public AnswerComment (String content, String nickname, String password, LocalDateTime createdAt, LocalDateTime updatedAt, Answer answer)
	{
		this.content = content;
		this.nickname = nickname;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.answer = answer;
	}

}
