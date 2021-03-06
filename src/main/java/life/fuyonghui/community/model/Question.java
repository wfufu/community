package life.fuyonghui.community.model;

import lombok.Data;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/13 1:21
 * @Version 1.0
 */
@Data
public class Question {
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

}
