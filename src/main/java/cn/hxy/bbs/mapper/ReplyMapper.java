package cn.hxy.bbs.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.dto.ReplyDetail;
import cn.hxy.bbs.model.Reply;

public interface ReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKeyWithBLOBs(Reply record);

    int updateByPrimaryKey(Reply record);
    
    Date getLastCreateDateByPostId(@Param("id")int id);
    
    List<ReplyDetail> getReplyDetailByPostId(@Param("postId")int postId);
    
    List<ReplyDetail> getReplyDetailByName(@Param("params")String params);
    
    List<ReplyDetail> getAdviceByName(@Param("params")String params);
    
    List<ReplyDetail> getReplyByUserId(@Param("userId") int userId,@Param("params")String params);
    
    List<ReplyDetail> findHotReply();
    
    int hideReplyById(@Param("id")int id,@Param("status") int status);
    
    int getFollor(@Param("postId")int postId,@Param("replyId")int replyId);
    
    List<Map> getReplyCreate();
}