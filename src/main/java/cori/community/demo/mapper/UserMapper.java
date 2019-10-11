package cori.community.demo.mapper;

import cori.community.demo.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @author 3plus2
 * @data 2019/10/2 17 51
 * @desercription
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(Integer id);

    @Select("select * from user where account_id=#{id}")
    User findByAccountId(@Param("id")String id);

    @Update("update  user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where account_id=#{accountId}")
    void update(User dbUser);
}
