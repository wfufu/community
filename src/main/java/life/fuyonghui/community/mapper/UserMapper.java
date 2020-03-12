package life.fuyonghui.community.mapper;

import life.fuyonghui.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    //#{}代表在没有batis编译的时候会把形参里面的token 放到这里面去
    //如果是类的话会自动放 如果不是的需要加一个@Param（）注解
    @Select("select * from user where token = #{token}")
    User fianByToken(@Param("token") String token);
}
