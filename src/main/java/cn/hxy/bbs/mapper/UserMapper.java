package cn.hxy.bbs.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    
    User doUserLogin(@Param("name") String name,@Param("password") String password);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int getUserIdByName(String name);
    
    String getUsernameById(int id);
    
    Boolean verifyUsername(String username);
    
    Set<String> getRolesByUsername(String username);
    
    String getEmailByNameOrEmail(@Param("nameOrEmail")String nameOrEmail);
    
    String getNameByEmail(@Param("email")String email);
    
    void updatePassword(@Param("name")String name,@Param("password")String password); 
}