package life.majiang.community.controller;

import life.majiang.community.mapper.LogMapper;
import life.majiang.community.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChangeController {
    @Autowired
    private LogMapper logMapper;

    @GetMapping("/change")
    public String change(){
        return "change";
    }

    @PostMapping("/change")
    public String doLog(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "newpassword", required = false) String newpassword,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("name", name);
        model.addAttribute("password", password);
        model.addAttribute("id", id);

        if (name ==null||name == ""){
            model.addAttribute("error", "姓名不能为空");
            return "change";
        }
        if (password ==null||password == ""){
            model.addAttribute("error", "密码不能为空");
            return "change";
        }if (newpassword ==null||newpassword == ""){
            model.addAttribute("error", "新密码不能为空");
            return "change";
        }

        Log log1 = new Log();
        log1 = logMapper.findByName(name);

        if (log1 ==null){
            model.addAttribute("error", "用户不存在");
            return "change";
        }

        if (log1.getPassword().equals(password)){
            log1.setPassword(newpassword);
            logMapper.update(log1);
        }
        else {
            model.addAttribute("error", "密码错误");
            return "change";
        }
        return "redirect:/";
    }
}
