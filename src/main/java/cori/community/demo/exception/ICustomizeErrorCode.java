package cori.community.demo.exception;

/**
 * @author 3plus2
 * @data 2019/10/16 16 28
 * @desercription
 */
public interface ICustomizeErrorCode {
    default String getMessage() {
        return null;
    }
}
