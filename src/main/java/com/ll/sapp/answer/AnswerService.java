package com.ll.sapp.answer;

import com.ll.sapp.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor //객체 생성과 관련됨
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    
    public void create(Question question, String content){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question); //어떤 질문에 대한 응답인지
        this.answerRepository.save(answer);
    }

}
