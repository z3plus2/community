package cori.community.demo.dto;

import lombok.Data;

/**
 * @author 3plus2
 * @data 2019/10/2 13 12
 * @desercription
 */
@Data
public class AccessToken {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
