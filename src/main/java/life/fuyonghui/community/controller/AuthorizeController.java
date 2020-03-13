package life.fuyonghui.community.controller;

import life.fuyonghui.community.dto.AccessTokenDTO;
import life.fuyonghui.community.dto.GithubUser;
import life.fuyonghui.community.mapper.UserMapper;
import life.fuyonghui.community.model.User;
import life.fuyonghui.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    //spring 通过这个注解会把这个对象预先实例化好，方法哦容器里面，通过Autowired就可以自动拿进来
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           //session从HttpServletRequest request 中获得
                           //spring会自动把上下文中的request放在这里面
//                           HttpServletRequest request,
//                           通过response去写入cookie
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
//        System.out.println(user.getName());
        if (githubUser != null && githubUser.getId() !=null) {
            //登录成功，写cookie 和session

            User user = new User();
            //用token代替之前的session
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            //插入数据库的过程就相当于写入session，所以不用再写入session了，相当于用数据库的存储代替了session的写入
            userMapper.insert(user);
            //写入cookie  通过response去写入cookie
            response.addCookie(new Cookie("token",token));

            //把user对象放到session里面
            //相当于在银行当中账户已经写入成功了
//            request.getSession().setAttribute("user", githubUser);
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
