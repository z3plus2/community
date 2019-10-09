package cori.community.demo.service;

import cori.community.demo.dto.PaginationDTO;
import cori.community.demo.dto.QuestionDTO;
import cori.community.demo.mapper.QuestionMapper;
import cori.community.demo.mapper.UserMapper;
import cori.community.demo.model.Question;
import cori.community.demo.model.User;
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

    public PaginationDTO list(Integer page, Integer size) {

        Integer totalCount=questionMapper.count();


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
        List<Question> questions=questionMapper.list(offset,size);
        for (Question question : questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);

        }
        paginationDTO.setQuestionDTO(questionDTOs);

        return paginationDTO;
    }


    public PaginationDTO list(Integer id, Integer page, Integer size) {
        Integer totalCount=questionMapper.countById(id);


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
        List<Question> questions=questionMapper.listById(id,offset,size);
        for (Question question : questions) {
            User user=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);

        }
        paginationDTO.setQuestionDTO(questionDTOs);

        return paginationDTO;
    }
}
