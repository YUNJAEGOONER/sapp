package com.ll.sapp.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Question - 데이터
//QuestionRepository - 데이터 관리자
//Repository에 저장 - QuestionRepository.save(Question 객체)
public interface QuestionRepository extends JpaRepository <Question, Integer>{

    //method의 내용은 jpa가 알아서 구현???
    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);

    Page<Question> findAll(Pageable pageable);
}
