package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.response.QuestionResponse;
import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MainPageController {

    private final QuestionService questionService;

    @GetMapping("")
    public Page<QuestionResponse> mainPage (@RequestParam(value = "category", defaultValue = "null")String category, @RequestParam(value = "sort", defaultValue = "createdAt") String sort, @RequestParam(value = "pagingIndex", defaultValue = "0") int pagingIndex, @RequestParam(value = "pagingSize", defaultValue = "10") int pagingSize) {
        if (category.equals("null"))
        {
            Page<QuestionResponse> paging = this.questionService.getQuestionResponsePages(pagingIndex, pagingSize, sort);
            return paging;
        }
        Page<QuestionResponse> paging = this.questionService.getQuestionResponsePages(pagingIndex, pagingSize, sort, category);
        return paging;
    }
}
