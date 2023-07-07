package com.peer.missionpeerflow;

import com.peer.missionpeerflow.dto.request.QuestionRequest;
import com.peer.missionpeerflow.dto.response.QuestionResponse;
import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.service.QuestionService;
import com.peer.missionpeerflow.util.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MissionPeerflowApplicationTests {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testService() {
		QuestionRequest questionRequest1 = new QuestionRequest("asdfasdf 어려웡", "jun", "1234",
				"miniRt", "postman so hard", LocalDateTime.now());
//
		this.questionService.create(questionRequest1);
//		questionRepository.save()
//		QuestionResponse questionResponse = this.questionService.getQuestionResponse(1L);
//		System.out.println(quesstionResponse.getTitle());
//		System.out.println(questionResponse.getContent());
	}
}
