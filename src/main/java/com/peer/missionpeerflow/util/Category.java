package com.peer.missionpeerflow.util;

import com.peer.missionpeerflow.exception.NotFoundException;
import java.util.Arrays;

public enum Category {
	MINISHELL(1, "MINISHELL"),
	FDF(2, "FDF"),
	FT_IRC(3, "FT_IRC");

	private final int code;
	private final String type;

	private Category(int code, String type) {
		this.code = code;
		this.type = type;
	}

	public static Category ofCode(int code) {
		return Arrays.stream(Category.values())
						.filter(e -> e.getCode() == code)
						.findAny()
						.orElseThrow(() -> new NotFoundException("존재하지 않는 카테고리입니다."));
	}

	public static Category ofType(String type) {
		return Arrays.stream(Category.values())
						.filter(e -> e.getType().equals(type))
						.findAny()
						.orElseThrow(() -> new NotFoundException("존재하지 않는 카테고리입니다."));
	}

	public int getCode() {
		return this.code;
	}

	public String getType() {
		return this.type;
	}
}
