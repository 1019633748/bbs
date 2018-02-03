package cn.hxy.bbs.mapper;

import java.util.Set;

import cn.hxy.bbs.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int getUserIdByName(String name);
    
    String getUsernameById(int id);
    
    Set<String> getRolesByUsername(String username);
}