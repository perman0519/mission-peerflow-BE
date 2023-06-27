package com.peer.missionpeerflow.service;

import com.peer.missionpeerflow.dto.IndexDTO;
import com.peer.missionpeerflow.entity.Question;
import com.peer.missionpeerflow.repository.QuestionRepository;
import com.peer.missionpeerflow.util.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndexService {
    private final QuestionRepository questionRepository;


    public Page<IndexDTO> getIndex(int pagingIndex, int pagingSize) {
        Pageable pageable = PageRequest.of(pagingIndex, pagingSize);
        Page<Question> questionsPage = questionRepository.findAll(pageable);
        return questionsPage.map(IndexDTO::toIndexDTO);
    }

    public Page<IndexDTO> getCategoryBoards(int pagingIndex, int pagingSize, Category category) {
        Pageable pageable = PageRequest.of(pagingIndex, pagingSize);
        Page<Question> questionsPage = questionRepository.findByCategory(pageable, category);
        return questionsPage.map(IndexDTO::toIndexDTO);
    }
}
