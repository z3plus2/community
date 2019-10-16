package cori.community.demo.exception;

/**
 * @author 3plus2
 * @data 2019/10/16 16 12
 * @desercription
 */
public class CustiomizeException extends RuntimeException{
    private String message;
    public CustiomizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
