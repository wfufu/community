package life.fuyonghui.community.mapper;

import life.fuyonghui.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/11 20:51
 * @Version 1.0
 */
@Mapper
public interface UserMapper {
    //mybatis做的事情就是当执行这条语句时，会把object里面的user下面的name等属性自动填充到#{}里面自动替换掉
    @Insert("insert into community.user(name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    //public reluctant可以去掉
    void insert(User user);
}
