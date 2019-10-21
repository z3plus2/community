package cori.community.demo.exception;

/**
 * @author 3plus2
 * @data 2019/10/16 16 12
 * @desercription
 */
public class CustiomizeException extends RuntimeException{
    private String message;
    private Integer code;
    public CustiomizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
        this.code=errorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
