package life.fuyonghui.community.service;

import life.fuyonghui.community.dto.QuestionDTO;
import life.fuyonghui.community.mapper.QuestionMapper;
import life.fuyonghui.community.mapper.UserMapper;
import life.fuyonghui.community.model.Question;
import life.fuyonghui.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fu Yonghui
 * @Date 2020/3/14 0:26
 * @Version 1.0
 */
//有这个service以后spring就会自动的去管理它
//有这个QuestionService的目的就是在这里面不仅仅可以使用QuestionMapper，还可以使用UserMapper，起到一个组装的作用
//当一个请求需要组装user的值 同时也需要组装question的时候，就需要一个中间层去做这个事情，习惯性的把这个中间层叫做service
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        //通过所有的questionMapper.list()查到所有的question对象
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //循环question对象
        for (Question question : questions) {
            //获取当前的user
            User user = userMapper.findById(question.getCreator());

            QuestionDTO questionDTO = new QuestionDTO();
            //第一步把所有的question对象放到questionDTO里面
//            questionDTO.setId(question.getId());//最古老的方法
            //spring 内置了一个很简单的方法   可以快速的吧前面对象的所有属性copy到后面的对象上
            BeanUtils.copyProperties(question, questionDTO);
            //还差最后一个值--user
            questionDTO.setUser(user);

            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
