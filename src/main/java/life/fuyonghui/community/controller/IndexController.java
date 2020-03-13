package life.fuyonghui.community.controller;

import life.fuyonghui.community.dto.QuestionDTO;
import life.fuyonghui.community.mapper.QuestionMapper;
import life.fuyonghui.community.mapper.UserMapper;
import life.fuyonghui.community.model.Question;
import life.fuyonghui.community.model.User;
import life.fuyonghui.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/9 22:33
 * @Version 1.0
 */
@Controller
public class IndexController {

    //注入usermapper
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    //当访问首页的时候 循环去看所有的cookie  找到cookie = token的cookie，然后拿到这个cookie去数据库里面查是不是有cookie这条记录，如果有 就把这个user方法session里面
    //然后前端就能根据页面这个数据去判断是否展示“我”还是“登录”
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();

        //判断cookie是否为0
        if (cookies != null && cookies.length != 0) {
            //for循环这个cookie 看看里面都有什么东西
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();

                    //传过去一个User就可以获取一个User对象
                    User user = userMapper.findByToken(token);

                    if (user != null) {
                        //满足条件就写到session里
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        List<QuestionDTO> questionList = questionService.list();
        model.addAttribute("questions", questionList);
//        //传过去一个User就可以获取一个User对象
//        User user = userMapper.fianByToken{token};
        return "index";
    }
}
