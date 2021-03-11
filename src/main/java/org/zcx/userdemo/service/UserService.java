package org.zcx.userdemo.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.zcx.userdemo.DAO.UserMapper;
import org.zcx.userdemo.VO.User;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> search(Map<String, Object> searchInfo) {
        return userMapper.search(searchInfo);
    }

    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }


    public int insert(User record) {
        return userMapper.insert(record);
    }


    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

}

