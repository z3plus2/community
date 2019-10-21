package cori.community.demo.dto;

import lombok.Data;

/**
 * @author 3plus2
 * @data 2019/10/17 16 30
 * @desercription
 */
@Data
public class CommentDTO {
    private Long parentId;
    private Integer type;
    private Integer commentatorId;
    private String description;
}
