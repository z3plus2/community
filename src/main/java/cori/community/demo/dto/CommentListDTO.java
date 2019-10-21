package cori.community.demo.dto;

import cori.community.demo.model.User;
import lombok.Data;

/**
 * @author 3plus2
 * @data 2019/10/17 16 30
 * @desercription
 */
@Data
public class CommentListDTO {
    private Long parentId;
    private Integer type;
    private Integer commentatorId;
    private String description;
    private Long id;
    private Long likeCount;
    private Long gmtModified;
    private Long gmtCreate;
    User user;
}
