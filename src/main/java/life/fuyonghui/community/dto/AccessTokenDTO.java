package life.fuyonghui.community.dto;

import lombok.Data;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/10 0:11
 * @Version 1.0
 */
//参数超过两个以上 最好做成对象的形式
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
