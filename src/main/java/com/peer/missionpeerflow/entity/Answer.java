package com.peer.missionpeerflow.entity;

import com.peer.missionpeerflow.dto.request.PostAnswerRequest;
import com.peer.missionpeerflow.dto.request.UpdateAnswerRequest;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
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
	private Question question;

	@Column(nullable = false)
	private Long recommend = 0L;

	@Column(nullable = false)
	private Boolean isAdopted;

	@OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AnswerComment> answerCommentList = new ArrayList<>();

	public Answer(PostAnswerRequest request, Question question) {
		this.question = question;
		this.nickname = request.getNickname();
		this.password = request.getPassword();
		this.content = request.getContent();
		this.createdAt = LocalDateTime.now();
		this.isAdopted = false;
	}

	public void update(UpdateAnswerRequest request) {
		this.content = request.getContent();
		this.updatedAt = LocalDateTime.now();
	}
}
