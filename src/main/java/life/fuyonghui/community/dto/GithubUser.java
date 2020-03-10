package life.fuyonghui.community.dto;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/10 0:32
 * @Version 1.0
 */
public class GithubUser {
    //通过 access_token 获取信息
    //githubUser 其实是githubProvider的一个返回值
    private String name;
    //防止以后用户暴增的时候越界 用long类型
    private Long id;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
