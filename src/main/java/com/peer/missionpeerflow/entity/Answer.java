package com.peer.missionpeerflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "answer")
@Entity
public class Answer extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long answerId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "question_id")
	@JsonIgnore
	private Question question;

	@Column(nullable = false)
	private Long recommend = 0L;

	@Column(nullable = false)
	private Boolean isAdopted;

	@OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AnswerComment> answerCommentList = new ArrayList<>();


	@Builder
	public Answer(Question question, Long recommend, boolean isAdopted, String content, String password, String nickname, List<AnswerComment> answerCommentList, LocalDateTime createdAt, LocalDateTime updatedAt){
		this.question = question;
		this.recommend = recommend;
		this.isAdopted = isAdopted;
		this.content = content;
		this.password = password;
		this.nickname = nickname;
		this.answerCommentList = answerCommentList;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public void update(String content, String nickname, LocalDateTime updatedAt){
		this.content = content;
		this.nickname = nickname;
		this.updatedAt = updatedAt;
	}
}
