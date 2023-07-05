package com.peer.missionpeerflow;

import com.peer.missionpeerflow.dto.request.QuestionRequest;
import com.peer.missionpeerflow.dto.response.QuestionResponse;
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

		QuestionRequest questionRequest1 = new QuestionRequest("으아아아아아아", "junssong", "1234",
				"this is so ㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㅁㄴㅇㄹㄴㅁㅇㄹ", "miniRt");

		this.questionService.create(questionRequest1);
		QuestionResponse questionResponse = this.questionService.getQuestionResponse(questionRepository.findById(1L).get());
		System.out.println(questionResponse.getTitle());
		System.out.println(questionResponse.getContent());
	}

}
