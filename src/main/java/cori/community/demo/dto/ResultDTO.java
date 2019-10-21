package cori.community.demo.dto;

import cori.community.demo.exception.CustomizeErrorCode;
import lombok.Data;

/**
 * @author 3plus2
 * @data 2019/10/17 18 01
 * @desercription
 */
@Data
public class ResultDTO {
    private  Integer code;
    private String message;

    public  static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public  static ResultDTO errorOf(CustomizeErrorCode errorCode){
      return errorOf(errorCode.getCode(),errorCode.getMessage());
    }

    public  static ResultDTO okOf(){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("ok");
        return resultDTO;
    }

    }
