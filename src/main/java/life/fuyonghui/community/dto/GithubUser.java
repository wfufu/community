package life.fuyonghui.community.dto;

import lombok.Data;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/10 0:32
 * @Version 1.0
 */
@Data
public class GithubUser {
    //通过 access_token 获取信息
    //githubUser 其实是githubProvider的一个返回值
    private String name;
    //防止以后用户暴增的时候越界 用long类型
    private Long id;
    private String bio;
    private String avatar_url;

}
