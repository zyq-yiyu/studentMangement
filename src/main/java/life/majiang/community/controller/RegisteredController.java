package life.majiang.community.controller;

import life.majiang.community.mapper.LogMapper;
import life.majiang.community.model.Log;
import life.majiang.community.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisteredController {
    @Autowired
    private LogService logService;

    @Autowired
    private LogMapper logMapper;

    @GetMapping("/registered")
    public String log() {
        return "registered";
    }

    @PostMapping("/registered")
    public String doLog(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "password", required = false) String password,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("name", name);
        model.addAttribute("password", password);
        model.addAttribute("id", id);

        if (name == null || name == "") {
            model.addAttribute("error", "姓名不能为空");
            return "registered";
        }
        if (password == null || password == "") {
            model.addAttribute("error", "密码不能为空");
            return "registered";
        }

        Log log1 = new Log();
        log1 = logMapper.findByName(name);
        if (log1 !=null){
            model.addAttribute("error", "用户已存在");
            return "registered";
        }

        Log log = new Log();
        log.setName(name);
        log.setPassword(password);
        logService.sign(log);
        model.addAttribute("error", "注册成功");
        return "/log";
    }
}