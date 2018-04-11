package cn.hxy.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.dto.UserDetail;
import cn.hxy.bbs.model.Friend;

public interface FriendMapper {
    int insert(Friend record);

    int insertSelective(Friend record);
      
    List<UserDetail> getFriendByUserId(@Param("userId")int userId);
    
    List<UserDetail> getFansByUserId(@Param("userId")int userId);
    
    int isAttention(@Param("currentUserId")int currentUserId,@Param("targetUserId")int targetUserId);
    
    int unfollow(@Param("currentUserId")int currentUserId,@Param("targetUserId")int targetUserId);
}