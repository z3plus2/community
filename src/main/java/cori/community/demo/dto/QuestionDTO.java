package cori.community.demo.dto;

import cori.community.demo.model.User;
import lombok.Data;

/**
 * @author 3plus2
 * @data 2019/10/3 19 07
 * @desercription
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}

