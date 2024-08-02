package com.ll.sapp.answer;

import com.ll.sapp.DataNotFoundException;
import com.ll.sapp.question.Question;
import com.ll.sapp.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor //객체 생성과 관련됨
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    
    public void create(Question question, String content, SiteUser author){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question); //어떤 질문에 대한 응답인지
        answer.setAuthor(author);
        this.answerRepository.save(answer);
    }

    public Answer getAnswer(Integer id){
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()){
            return answer.get();
        }
        else{
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(Answer answer, String content){
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    public void delete(Answer answer){
        this.answerRepository.delete(answer);
    }

}
