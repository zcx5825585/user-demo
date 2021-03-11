package org.zcx.userdemo.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.zcx.userdemo.VO.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    User selectByPrimaryKey(Long id);

    List<User> search(Map<String, Object> searchInfo);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int updateByPrimaryKey(User record);

}