package cn.hxy.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.model.Friend;

public interface FriendMapper {
    int insert(Friend record);

    int insertSelective(Friend record);
    
    int getAttention(int id);
    
    int getFans(int id);
    
    
    List <Integer> getAttentionIdList(int id);
    
   // List<Map <String,String>> getAttentionIdList(int id);
    
    List <Integer> getFansIdList(int id);
    
    boolean isAttention(@Param("userId")int userId, @Param("targetId")int targetId);
    
    int removeAttention(@Param("userId")int userId,@Param("friendId")int friendId);
}