package cori.community.demo.service;

import cori.community.demo.dto.CommentDTO;
import cori.community.demo.dto.CommentListDTO;
import cori.community.demo.enums.CommentTypeEnum;
import cori.community.demo.exception.CustiomizeException;
import cori.community.demo.exception.CustomizeErrorCode;
import cori.community.demo.mapper.CommentMapper;
import cori.community.demo.mapper.QuestionExtMapper;
import cori.community.demo.mapper.QuestionMapper;
import cori.community.demo.mapper.UserMapper;
import cori.community.demo.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 3plus2
 * @data 2019/10/17 18 36
 * @desercription
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

   /** @Transactional  注解 事务性**/
    public void insert(Comment comment) {
        if (comment.getParentId()==null||comment.getParentId()==0){
            throw new CustiomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
        }
       if (comment.getType()== CommentTypeEnum.QUESTION.getType()) {

           commentMapper.insert(comment);
           questionExtMapper.addQuestionComment(comment.getParentId());
       }
    }

    public List<CommentListDTO> listByQuestionId(Integer id) {
        CommentExample example = new CommentExample();
        example.createCriteria().andParentIdEqualTo((long)id).andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        List<Comment> comments = commentMapper.selectByExampleWithBLOBs(example);

        if (comments.size()==0){
            return new ArrayList<>();
        }
        //获取去重的评论人
        List<Integer> collect = comments.stream().map(comment -> comment.getCommentatorId()).collect(Collectors.toList());

//        获取评论人转会为map
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(collect);
        List<User> users = userMapper.selectByExample(userExample);

        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<CommentListDTO> commentDTOS = comments.stream().map(comment -> {
            CommentListDTO commentListDTO = new CommentListDTO();
            BeanUtils.copyProperties(comment,commentListDTO);
            commentListDTO.setUser(userMap.get(comment.getCommentatorId()));
            return commentListDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
