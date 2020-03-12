package life.fuyonghui.community.mapper;

import life.fuyonghui.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/13 1:16
 * @Version 1.0
 */
@Mapper
public interface QuestionMapper {

    //其他值有默认值0  不用写
    @Insert("insert into community.question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag}")
    void create(Question question);
}
