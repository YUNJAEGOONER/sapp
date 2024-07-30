package com.ll.sapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SappApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void testJpa() {
        //repo_save();
        //find_all();
        //find_by_id();
        //find_by_subject();
        //find_by_subject_content();
        find_like();
    }

    //Question 테이블에 데이터(Question)를 저장하는 코드
    void repo_save(){
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);  // 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);  // 두번째 질문 저장
    }
    
    //Question 테이블에 있는 모든 데이터(Question)를 조회
    void find_all(){
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    //Question 테이블에서 특정 ID를 갖는 데이터(Question)를 조회
    void find_by_id(){
        //findById의 실행 결과가 Null일 경우를 대비 -> Optional?
        //Optional -> 하나의 Question만을 가질 수 있음
        Optional<Question> oq = this.questionRepository.findById(1);
        if (oq.isPresent()){
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }

    void find_by_subject(){
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, q.getId());
    }

    void find_by_subject_content(){
        Question q = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        assertEquals(1, q.getId());
    }

    void find_like(){
        List<Question> qList = this.questionRepository.findBySubjectLike("%sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }
}
