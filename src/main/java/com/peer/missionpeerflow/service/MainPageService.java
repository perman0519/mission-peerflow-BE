package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.response.QuestionResponse;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.util.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final QuestionRepository questionRepository;

    public Page<QuestionResponse> getQuestionResponsePages(int pageIndex, int pagingSize, String sort) {
        Pageable pageable = PageRequest.of(pageIndex, pagingSize, Sort.by(sort).descending());

        Page<Question> questionPages = this.questionRepository.findAll(pageable);
        Page<QuestionResponse> questionResponsePages = questionPages.map(m -> QuestionResponse.fromQuestion(m));
        return  questionResponsePages;
    }

    public Page<QuestionResponse> getQuestionResponsePages(int pageIndex, int pagingSize, String sort, String category) {
        Pageable pageable = PageRequest.of(pageIndex, pagingSize, Sort.by(sort).descending());
        Page<Question> questionPages = this.questionRepository.findByCategory(Category.ofType(category), pageable);
        Page<QuestionResponse> questionResponsePages = questionPages.map(m -> QuestionResponse.fromQuestion(m));
        return  questionResponsePages;
    }
}
