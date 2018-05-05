package cn.hxy.bbs.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.dto.ReplyDetail;
import cn.hxy.bbs.mapper.ReplyMapper;
import cn.hxy.bbs.model.Reply;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public int addReply(Reply reply, int postId,HttpSession session) {
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		reply.setUserId(user.getId());
		reply.setPostId(postId);
		return replyMapper.insertSelective(reply);
	}
	
	@Override
	public int addAdviceContent(Reply reply, int postId,HttpSession session) {
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		reply.setUserId(user.getId());
		reply.setPostId(postId);
		reply.setStatus((byte)9);
		return replyMapper.insertSelective(reply);
	}

	@Override
	public List<Reply> getCheckReply() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReplyDetail> getReplyDetailByPostId(int postId) {
		return replyMapper.getReplyDetailByPostId(postId);
	}

	@Override
	public List<ReplyDetail> getReplyDetailByName(String params) {
		return replyMapper.getReplyDetailByName(params);
	}

	@Override
	public int hideReplyById(int id, int status) {
		return replyMapper.hideReplyById(id, status);
	}


	@Override
	public List<ReplyDetail> getReplyByUserId(int userId, String params) {
		return replyMapper.getReplyByUserId(userId, params);
	}

	@Override
	public List<ReplyDetail> getAdviceByName(String params) {
		return replyMapper.getAdviceByName(params);
	}

	@Override
	public List<ReplyDetail> findHotReply() {
		return replyMapper.findHotReply();
	}

	@Override
	public String reply(Reply reply, HttpSession session) {
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		reply.setUserId(user.getId());
		replyMapper.insertSelective(reply);
		return "SUC";
	}

	@Override
	public int getFollor(int postId, int replyId) {
		return replyMapper.getFollor(postId, replyId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getReplyCreate() {
		return replyMapper.getReplyCreate();
	}

}
