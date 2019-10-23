package cori.community.demo.exception;

import javax.lang.model.type.IntersectionType;

/**
 * @author 3plus2
 * @data 2019/10/16 16 27
 * @desercription
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
//查找不到
    QUESTION_NOT_FOUND(2001,"你找的问题不存在了"),
    TARGET_NOT_FOUND(2002,"未选中问题或者评论，无法评论"),
    NO_LOGIN(2003,"未登录"),
    NO_COMMENT(2004,"输入不能为空");

    private String message;
    private Integer code;

     CustomizeErrorCode(Integer code,String message){
        this.code=code;
         this.message=message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
