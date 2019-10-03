package cori.community.demo.model;

import lombok.Data;

/**
 * @author 3plus2
 * @data 2019/10/3 15 48
 * @desercription
 */
@Data
public class Question {
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
}
