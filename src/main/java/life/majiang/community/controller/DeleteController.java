package life.majiang.community.controller;

import life.majiang.community.mapper.LogMapper;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.Log;
import life.majiang.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteController {
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable(name = "id") Integer id
    ) {
        Log log =new Log();
        log.setId(id);
        questionMapper.delete(id);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String doDelete(
            @RequestParam(value = "id", required = false) Integer id
    ){
        Log log =new Log();
        log.setId(id);
        questionMapper.delete(id);
        return "redirect:/";
    }
}
