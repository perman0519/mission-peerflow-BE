package com.peer.missionpeerflow.entity;

import com.peer.missionpeerflow.util.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class QuestionTest {

    @Autowired
    IndexService indexService;

    @Autowired
    QuestionRepository questionRepository;
    void makeQuestion()
    {
        Question question = new Question();
        question.setTitle("안녕하세요");
        question.setCategory(Category.MINISHELL);
        question.setRecommend(1L);
        question.setContent("메우 안녕하세요");
        question.setCreatedAt(LocalDateTime.now());
        question.setNickname("용훈");
        question.setPassword("1234");
        questionRepository.save(question);
    }
}