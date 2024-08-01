package com.ll.sapp.question;

import com.ll.sapp.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/question") //URL 프리픽스
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    //URL 매핑 -> prefix + /list 
    // "/question/list"에 매핑
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        //repository에 있는 모든 question을 가져온다.
        Page<Question> paging = this.questionService.getList(page);

        //Model 객체 : 자바 클래스와 템플릿간의 연결 고리 역할
        //Model 객체에 값을 담아 두면 템플릿에서 그 값을 사용할 수 있다.
        //question_list라는 이름으로 questionList(Question객체들의 집합)를 Model객체에 저장
        model.addAttribute("paging", paging);
        return "question_list";//resource의 question_list.html 파일이 reuturn 된다.
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("detailed_question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }


}
