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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final QuestionRepository questionRepository;

    private Specification<Question> search(String title) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                return cb.like(q.get("title"), "%" + title + "%"); // 제목
            }
        };
    }

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
    public Page<QuestionResponse> getSearchQuestionResponsePages(String title, String sort) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(sort).descending());
        Specification<Question> spec = this.search(title);
        Page<Question> questionPages = this.questionRepository.findAll(spec, pageable);
        Page<QuestionResponse> questionResponsePages = questionPages.map(m -> QuestionResponse.fromQuestion(m));
        return  questionResponsePages;
    }
}
