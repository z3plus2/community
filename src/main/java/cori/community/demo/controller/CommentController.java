package cori.community.demo.controller;

import cori.community.demo.dto.CommentDTO;
import cori.community.demo.dto.ResultDTO;
import cori.community.demo.exception.CustomizeErrorCode;
import cori.community.demo.mapper.CommentMapper;
import cori.community.demo.model.Comment;
import cori.community.demo.model.User;
import cori.community.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 3plus2
 * @data 2019/10/17 16 16
 * @desercription
 */
@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        if (user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment=new Comment();
        comment.setCommentatorId(user.getId());
        comment.setParentId(commentDTO.getParentId());
        comment.setDescription(commentDTO.getDescription());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
