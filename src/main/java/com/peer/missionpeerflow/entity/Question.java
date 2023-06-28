package com.peer.missionpeerflow.entity;

import com.peer.missionpeerflow.dto.request.QuestionRequest;
import com.peer.missionpeerflow.util.Category;
import com.peer.missionpeerflow.util.CategoryAttributeConverter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "question")
@Entity
public class Question extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	@Convert(converter = CategoryAttributeConverter.class)
	private Category category;

	@Column(nullable = false)
	private Long recommend = 0L;

	@Column(nullable = false)
	private Long view = 0L;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Answer> answerList = new ArrayList<>();

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<QuestionComment> questionCommentList = new ArrayList<>();

	public Question (QuestionRequest request) {
		this.title = request.getTitle();
		this.category = request.getCategory();
		this.nickname = request.getNickname();
		this.password = request.getPassword();
		this.content = request.getContent();
		this.createdAt = LocalDateTime.now();
	}

	public void update(QuestionRequest request) {
		this.title = request.getTitle();
		this.category = request.getCategory();
		this.content = request.getContent();
		this.updatedAt = LocalDateTime.now();
	}

	public void addAnswer(Answer answer) {
		this.answerList.add(answer);
	}
}
