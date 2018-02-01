package cn.hxy.bbs.mapper;

import cn.hxy.bbs.model.Friend;

public interface FriendMapper {
    int insert(Friend record);

    int insertSelective(Friend record);
    
    int getAttention(int id);
    
    int getFans(int id);
}