package com.ll.sapp;

import com.ll.sapp.answer.Answer;
import com.ll.sapp.answer.AnswerRepository;
import com.ll.sapp.question.Question;
import com.ll.sapp.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SappApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    //명시적으로 transaction을 활성화
    @Transactional
    @Test
    void testJpa() {
        //repo_save();
        //find_all();
        //find_by_id();
        //find_by_subject();
        //find_by_subject_content();
        //find_like();
        //modify_subject();
        //delete_question();
        //create_answer();
        //answer_find_by_id();
        answer_find();
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
            Question q = oq.get(); //하나의 객체만 존재할 수 있음으로 get에 대한 index 존재 x
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

    void modify_subject(){
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
    }

    void delete_question(){
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }

    //answer 저장하기
    void create_answer(){
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        
        //Answer 객체를 생성
        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q); //어떤 질문의 답변인지
        a.setCreateDate(LocalDateTime.now());//현재시간으로 설정
        this.answerRepository.save(a);//answerRepository에 answer 저장
    }

    //answer 조회하기
    void answer_find_by_id(){
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        //Answer에 대한 question을 가져와서 해당 question의 id를 가져옴
        assertEquals(2, a.getQuestion().getId());
    }

    //question을 찾고 그에 해당하는 answer 찾기
    void answer_find(){
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();
        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
    }

}
