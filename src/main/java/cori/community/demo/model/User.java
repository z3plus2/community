package cori.community.demo.model;

import jdk.nashorn.internal.ir.debug.PrintVisitor;
import lombok.Data;

/**
 * @author 3plus2
 * @data 2019/10/2 17 53
 * @desercription
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
