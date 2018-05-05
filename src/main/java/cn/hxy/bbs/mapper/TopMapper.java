package cn.hxy.bbs.mapper;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.model.Top;

public interface TopMapper {
    int insert(Top record);

    int insertSelective(Top record);
    
    int getStatus(@Param("userId")int userId,@Param("replyId")int replyId);
    
    boolean isClick(@Param("userId")int userId,@Param("replyId")int replyId);
    
    int updateStatus(Top top);
    
    int findTop(@Param("replyId")int replyId);
    
    int findDown(@Param("replyId")int replyId);
    
    int deleteByUserIdAndReplyId(@Param("userId")int userId,@Param("replyId")int replyId);
}