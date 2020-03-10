package life.fuyonghui.community.controller;

import life.fuyonghui.community.dto.AccessTokenDTO;
import life.fuyonghui.community.dto.GithubUser;
import life.fuyonghui.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/10 0:05
 * @Version 1.0
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    //去配置文件里读取key是github.client.id的value 把它赋值到clientId
    //下同
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           //session从HttpServletRequest request 中获得
                           //spring会自动把上下文中的request放在这里面
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
//        System.out.println(user.getName());
        if (user != null) {
            //登录成功，写cookie 和session

            //把user对象放到session里面
            //相当于在银行当中账户已经写入成功了
            request.getSession().setAttribute("user", user);
            //把地址后面的后缀去掉  重定向到index页面
            //redirect返回的是路径 所以写“/” 而不是 “index”
            return "redirect:/";
        } else {
            //登录失败，重新登录

            return "redirect:/";
        }
//        return "index";
    }
}
