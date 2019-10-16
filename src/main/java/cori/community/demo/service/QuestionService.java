package cori.community.demo.service;

import cori.community.demo.dto.PaginationDTO;
import cori.community.demo.dto.QuestionDTO;
import cori.community.demo.exception.CustiomizeException;
import cori.community.demo.exception.CustomizeErrorCode;
import cori.community.demo.mapper.QuestionExtMapper;
import cori.community.demo.mapper.QuestionMapper;
import cori.community.demo.mapper.UserMapper;
import cori.community.demo.model.Question;
import cori.community.demo.model.QuestionExample;
import cori.community.demo.model.User;
import cori.community.demo.model.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 3plus2
 * @data 2019/10/3 19 12
 * @desercription
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDTO list(Integer page, Integer size) {

        Integer totalCount=(int)questionMapper.countByExample(new QuestionExample());


        PaginationDTO paginationDTO=new PaginationDTO();
        paginationDTO.setPagination(totalCount,page,size);

        if (page<1){
            page=1;
        }
        if (page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        Integer offset=size*(page-1);
        List<QuestionDTO> questionDTOs=new ArrayList<>();



        List<Question> questions=questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        for (Question question : questions) {
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);

        }
        paginationDTO.setQuestionDTO(questionDTOs);

        return paginationDTO;
    }


    public PaginationDTO list(Integer id, Integer page, Integer size) {
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(id);
        Integer totalCount=(int)questionMapper.countByExample(example);


        PaginationDTO paginationDTO=new PaginationDTO();
        paginationDTO.setPagination(totalCount,page,size);

        if (page<1){
            page=1;
        }
        if (page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        List<QuestionDTO> questionDTOs=new ArrayList<>();
        Integer offset=size*(page-1);


        List<Question> questions=questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));

        for (Question question : questions) {
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);

        }

        paginationDTO.setQuestionDTO(questionDTOs);

        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question=questionMapper.selectByPrimaryKey(id);
        if (question==null){
            throw new CustiomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO=new QuestionDTO();
        User user=userMapper.selectByPrimaryKey(question.getCreator());
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrEdit(Question question) {
        if(question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            question.setGmtModified(System.currentTimeMillis());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int update=questionMapper.updateByExampleSelective(question, example);
            if (update!=1){
                throw new CustiomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void addViewCount(Integer id) {
        questionExtMapper.addViewCount(id);
    }
}
