package life.fuyonghui.community.dto;

import life.fuyonghui.community.model.User;
import lombok.Data;

import java.security.PrivateKey;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/14 0:21
 * @Version 1.0
 */
// 其实和question一样 ，只是多了个User对象
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
