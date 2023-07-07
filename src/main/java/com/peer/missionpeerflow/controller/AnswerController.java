package com.peer.missionpeerflow.controller;

import com.peer.missionpeerflow.dto.request.AnswerRequest;
import com.peer.missionpeerflow.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/answer")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("")
    public String create(@Valid @RequestBody AnswerRequest answerRequest)
    {
        answerService.create(answerRequest);
        return "ok";
    }

//    @PutMapping("/{id}")
//    public String maodify(@PathVariable("id") Long answerId, @RequestBody AnswerRequest answerRequest)
//    {
//        return "ok";
//    }
//
//    @PostMapping("/{id}")
//    public String delete(@PathVariable("id") Long answerId, @RequestBody AnswerRequest answerRequest)
//    {
//        return "ok";
//    }
//    스웨거 장단점 적용이 쉬움 코드범벅 peer개발 springRestDocs
}
