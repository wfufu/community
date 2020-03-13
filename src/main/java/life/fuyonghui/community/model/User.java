package life.fuyonghui.community.model;

import lombok.Data;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/11 20:56
 * @Version 1.0
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;


}
