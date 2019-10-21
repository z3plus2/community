package cori.community.demo.enums;

/**
 * @author 3plus2
 * @data 2019/10/17 18 31
 * @desercription
 */
public enum CommentTypeEnum {
//    1问题类型2评论类型
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type){
        this.type=type;
    }
}
