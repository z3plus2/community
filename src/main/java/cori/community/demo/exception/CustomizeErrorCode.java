package cori.community.demo.exception;

/**
 * @author 3plus2
 * @data 2019/10/16 16 27
 * @desercription
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
//查找不到
    QUESTION_NOT_FOUND("你找的问题不存在了");

    private String message;

     CustomizeErrorCode(String message){
        this.message=message;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
