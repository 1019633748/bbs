package cn.hxy.bbs.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.dto.UserDetail;
import cn.hxy.bbs.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    

  //��¼
      User login(@Param("nickname")String nickname,@Param("password")String password);
      
  //ע��
      int register(User user);
      
      List<UserDetail> getAdminUserDetailByName(@Param("params")String params);
      
      List<UserDetail> getFollowListByUserId(@Param("userId")int userId,@Param("params")String params);
      
      List<UserDetail> getFansListByUserId(@Param("userId")int userId,@Param("params")String params);
      
      int hideUserById(@Param("id")int id, @Param("status")int status);
      
      User getUserByName(@Param("name")String name);
      
      
      //Personal
      UserDetail getUserDetailByUserId(@Param("userId")int userId);
      
      String getRoleByUserId(@Param("userId")int userId);
      
      //ͨ���ǳƻ���������Ҫ�޸������user
      User getUserByNameOrEmail(@Param("nameOrEmail")String nameOrEmail);
      
      //������
      
      int updatePassword(@Param("newPassword")String newPassword,@Param("id")int id);
      
      List<Map> getMaleFemaleAmount();
      
      List<Map> getCreateDate();
}