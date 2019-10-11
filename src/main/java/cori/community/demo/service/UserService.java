package cori.community.demo.service;

import cori.community.demo.mapper.UserMapper;
import cori.community.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 3plus2
 * @data 2019/10/11 19 41
 * @desercription
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public  void createOrUpdate(User user) {
       User dbUser= userMapper.findByAccountId(user.getAccountId());
       if (dbUser==null){
           user.setGmtCreate(System.currentTimeMillis());
           user.setGmtModified(user.getGmtCreate());
           userMapper.insert(user);
       }else {
           dbUser.setGmtModified(System.currentTimeMillis());
           dbUser.setAvatarUrl(user.getAvatarUrl());
           dbUser.setName(user.getName());
           dbUser.setToken(user.getToken());
           userMapper.update(dbUser);
       }
    }
}
