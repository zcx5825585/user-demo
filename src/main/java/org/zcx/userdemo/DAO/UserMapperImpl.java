package org.zcx.userdemo.DAO;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.zcx.userdemo.VO.User;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    public static Map<Long,User> database=new HashMap<>();

    public long index=0L;

    @Override
    public User selectByPrimaryKey(Long id) {
        return database.get(id);
    }

    @Override
    public List<User> search(Map<String, Object> searchInfo) {
        List<User> list=new ArrayList<>(database.values());
        if (!StringUtils.isEmpty(searchInfo.get("id"))){
            list=list.stream().filter(user -> user.getId()==(Long) searchInfo.get("id")).collect(Collectors.toList());
        }
        if (!StringUtils.isEmpty(searchInfo.get("userName"))){
            list=list.stream().filter(user -> user.getUserName().contains((String)searchInfo.get("userName"))).collect(Collectors.toList());
        }
        if (!StringUtils.isEmpty(searchInfo.get("realName"))){
            list=list.stream().filter(user -> user.getRealName().contains((String)searchInfo.get("realName"))).collect(Collectors.toList());
        }
        int page= (int) searchInfo.get("page");
        if (page>0){
            page=page-1;
        }else {
            page=0;
        }
        int rows= (int) searchInfo.get("rows");
        list=list.stream().sorted((_1,_2)->{
            return _1.getId()>_2.getId()?1:-1;
        }).skip(rows*page).limit(rows).collect(Collectors.toList());
        return list;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        database.remove(id);
        return 1;
    }

    @Override
    public int insert(User record) {
        index++;
        record.setId(index);
        record.setCreateTime(new Date());
        database.put(record.getId(),record);
        return 1;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        User old=database.get(record.getId());
        if (old==null){
            return 0;
        }else {
            database.put(record.getId(), record);
            return 1;
        }
    }
}
