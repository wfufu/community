package life.fuyonghui.community.provider;

import com.alibaba.fastjson.JSON;
import life.fuyonghui.community.dto.AccessTokenDTO;
import life.fuyonghui.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/10 0:10
 * @Version 1.0
 */
@Component
public class GithubProvider {
    //创建一个package  provider包隔离不同的业务
    //提供者的意思  提供一些对第三方支持的能力

    //Controller 是把当前的类作为路由的承载者
    //Component仅仅是把当前的类初始到Spring路由的上下文
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            //拆分
            String token = string.split("&")[0].split("=")[1];
            return token;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();

            //把String的JSON对象自动的转换解析成java的类对象，不用我们一点一定去解析String了，很便捷
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }
}
