package com.peer.missionpeerflow;

import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MissionPeerflowApplicationTests {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testService() {
//		QuestionRequest questionRequest1 = new QuestionRequest("asdfasdf 어려웡", "jun", "1234",
//				"miniRt", "postman so hard", LocalDateTime.now());
////
//		this.questionService.create(questionRequest1);
		questionService.updateView(1L);
	}
}
