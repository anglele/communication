package com.angle.communication.service;

import com.angle.communication.Mapper.QuestionMapper;
import com.angle.communication.Mapper.UserMapper;
import com.angle.communication.Model.Question;
import com.angle.communication.Model.User;
import com.angle.communication.dto.PageInationDto;
import com.angle.communication.dto.QuestionDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//组装user和question表 这曾主要是主页面展示问题列表和相对应得发帖人信息 加入分页面
public class QuestionService {
@Autowired
    private QuestionMapper questionMapper;
@Autowired
private UserMapper userMapper;
    public PageInationDto list(Integer page,Integer size) {

        PageInationDto pageInationDto = new PageInationDto();
        //分页
        Integer totalCount = questionMapper.count();
        pageInationDto.setPages(totalCount,page,size);
        if (page<1){
            page =  1;
        }

        if (page > pageInationDto.getTotalPage()){
page = pageInationDto.getTotalPage();
        }
        List<QuestionDto> questionDtoList=new ArrayList<>();
        Integer offset = size*(page - 1);
        List<Question> questions = questionMapper.list(offset,size);
        //查询creator发帖人个人新信息 遍历
        for (Question question:questions){
           User user = userMapper.findById(question.getCreator());
           //user表中查询这个creator得其他对应信息
          QuestionDto  questionDTO = new QuestionDto();

            BeanUtils.copyProperties(question,questionDTO);
            //把queston对象得所有 属性放进questondto中
            questionDTO.setUser(user);
        questionDtoList.add(questionDTO);
        }
  pageInationDto.setQuestions(questionDtoList);
        Integer totlecount = questionMapper.count();
        pageInationDto.setPages(totlecount,page,size);
        return  pageInationDto;
    }

    public PageInationDto list(Integer userId, Integer page, Integer size) {
        PageInationDto pageInationDto = new PageInationDto();
        //分页
        Integer totalCount = questionMapper.countByUserId(userId);
        pageInationDto.setPages(totalCount,page,size);
        if (page<1){
            page =  1;
        }

        if (page > pageInationDto.getTotalPage()){
            page = pageInationDto.getTotalPage();
        }

        //size*(page-1)
        List<QuestionDto> questionDtoList=new ArrayList<>();
        Integer offset = size*(page - 1);
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        //查询creator发帖人个人新信息 遍历
        for (Question question:questions){
            User user = userMapper.findById(question.getCreator());
            //user表中查询这个creator得其他对应信息
            QuestionDto  questionDTO = new QuestionDto();

            BeanUtils.copyProperties(question,questionDTO);
            //把queston对象得所有 属性放进questondto中
            questionDTO.setUser(user);
            questionDtoList.add(questionDTO);
        }
        pageInationDto.setQuestions(questionDtoList);
        Integer totlecount = questionMapper.count();
        pageInationDto.setPages(totlecount,page,size);
        return  pageInationDto;
}


    public QuestionDto getById(Integer id) {
        QuestionDto question = questionMapper.getById(id);
        QuestionDto  questionDTO = new QuestionDto();
        BeanUtils.copyProperties(question,questionDTO);
        return questionDTO;
    }

}
