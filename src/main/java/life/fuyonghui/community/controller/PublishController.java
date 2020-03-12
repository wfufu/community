package life.fuyonghui.community.controller;

import life.fuyonghui.community.mapper.QuestionMapper;
import life.fuyonghui.community.mapper.UserMapper;
import life.fuyonghui.community.model.Question;
import life.fuyonghui.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/13 0:17
 * @Version 1.0
 */
@Controller
public class PublishController {

    //自动注入QuestionMapper
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    //如果是get就去渲染页面
    //如果是post就去执行请求
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            //接收参数
            @RequestParam("description") String description,
            @RequestParam("title") String title,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {

        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.fianByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }

        if (user == null) {
            model.addAttribute("error", "用户未登录！");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionMapper.create(question);
        //返回首页 重新展示一下发布的内容
        return "redirect:/";
    }
}
