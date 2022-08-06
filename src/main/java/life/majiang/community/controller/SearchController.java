package life.majiang.community.controller;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.Question;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;
    @GetMapping("/search")
    public String search(){
        return "search";
    }
    @PostMapping("/search")
    public String doSearch(
            @RequestParam(value = "search", required = false) String search,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("search",search);
        Question question1 = new Question();
        question1 = questionMapper.findByDescription(search);
        if (question1 ==null){
            model.addAttribute("error", "查无此人");
            return "search";
        }
        QuestionDTO question = questionService.getBySearch(search);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        model.addAttribute("question",questionDTO);
        return "search";
    }
}
