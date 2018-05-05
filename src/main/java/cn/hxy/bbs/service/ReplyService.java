package cn.hxy.bbs.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.hxy.bbs.dto.ReplyDetail;
import cn.hxy.bbs.model.Reply;

public interface ReplyService {

	int addReply(Reply reply,int postId,HttpSession session);
	
	int addAdviceContent(Reply reply,int postId,HttpSession session);
	
	List<Reply> getCheckReply();
	
	List<ReplyDetail> getReplyDetailByPostId(int postId);
	
	List<ReplyDetail> getReplyDetailByName(String params);
	
	List<ReplyDetail> getAdviceByName(String params);
	
	int hideReplyById(int id,int status);
	
	List<ReplyDetail> getReplyByUserId( int userId,String params);
	
	List<ReplyDetail> findHotReply();
	
	String reply(Reply reply,HttpSession session);
	
	int getFollor(int postId,int replyId);
	
	@SuppressWarnings("rawtypes")
	List<Map> getReplyCreate();
}
