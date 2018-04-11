package cn.hxy.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.dto.VerifyPost;
import cn.hxy.bbs.dto.VerifyReply;
import cn.hxy.bbs.model.Verify;

public interface VerifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Verify record);

    int insertSelective(Verify record);

    Verify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Verify record);

    int updateByPrimaryKey(Verify record);
    
    int getRecordByUserIdAndPostId(@Param("userId")int userId,@Param("postId")int postId);
    
    int getRecordByUserIdAndReplyId(@Param("userId")int userId,@Param("replyId")int postId);
    
    List<VerifyPost> getAuditPostByParams(@Param("params")String params);
    
    List<VerifyReply> getAuditReplyByParams(@Param("params")String params);
    
    int hideVerifyById(@Param("id")int id, @Param("status")int status);
}