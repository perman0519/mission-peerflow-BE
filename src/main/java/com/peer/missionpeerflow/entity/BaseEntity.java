package com.peer.missionpeerflow.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
	@Column(nullable = false, columnDefinition = "VARCHAR(10)")
	private String nickname;

	@Column(nullable = false, columnDefinition = "VARCHAR(10)")
	private String password;

	@Column(nullable = false)
	@Lob
	private String content;

	@Column(nullable = false)
	private LocalDateTime created;

	private LocalDateTime updated;
}
