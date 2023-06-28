package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.IndexDTO;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.service.IndexService;
import com.peer.missionpeerflow.util.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class IndexController {

    private final IndexService indexService;

    private final QuestionRepository questionRepository;
    @GetMapping("/test1")
    public void test()
    {
            Question question = new Question();
            question.setTitle("안녕하세요");
            question.setView(1L);
            question.setCategory(Category.MINISHELL);
            question.setRecommend(1L);
            question.setContent("메우 안녕하세요");
            question.setCreatedAt(LocalDateTime.now());
            question.setNickname("용훈");
            question.setPassword("1234");
            questionRepository.save(question);
    }

    @GetMapping("/test2")
    public void test2()
    {
        Question question = new Question();
        question.setTitle("안녕하세요222");
        question.setView(1L);
        question.setCategory(Category.FT_IRC);
        question.setRecommend(1L);
        question.setContent("메우 안녕하세요222");
        question.setCreatedAt(LocalDateTime.now());
        question.setNickname("형찬");
        question.setPassword("1234");
        questionRepository.save(question);
    }

    @GetMapping("/test3")
    public void test3()
    {
        Question question = new Question();
        question.setTitle("안녕하세요33333");
        question.setView(1L);
        question.setCategory(Category.MINIRT);
        question.setRecommend(1L);
        question.setContent("메우 안녕하세요3333333");
        question.setCreatedAt(LocalDateTime.now());
        question.setNickname("소현");
        question.setPassword("1234");
        questionRepository.save(question);
    }

    @GetMapping("")
    public ResponseEntity<Page<IndexDTO>> getIndex(
            @RequestParam(value = "pagingIndex", defaultValue = "0") int pagingIndex,
            @RequestParam(value = "pagingSize", defaultValue = "10") int pagingSize,
            @RequestParam(required = false) String category) {
        Page<IndexDTO> indexDTOS;
        if (category == null) {
            indexDTOS = indexService.getIndex(pagingIndex, pagingSize);
        }
        else {
            Category cate = Category.ofType(category);
            indexDTOS = indexService.getCategoryBoards(pagingIndex, pagingSize, cate);
        }

        if (indexDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(indexDTOS, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<IndexDTO>> getIndex(
            @RequestParam(value = "pagingIndex", defaultValue = "0") int pagingIndex,
            @RequestParam(value = "pagingSize", defaultValue = "10") int pagingSize,
            @RequestParam("title") String title,
            @RequestParam(value = "sort", defaultValue = "latest") String sort)
    {
        Page<IndexDTO> searchResult = indexService.search(pagingIndex, pagingSize, title, sort);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

}
