package life.fuyonghui.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/9 22:33
 * @Version 1.0
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name",name);
        return "hello";
    }
}
