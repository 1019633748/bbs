package cn.hxy.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.dto.VerifyPost;
import cn.hxy.bbs.dto.VerifyReply;
import cn.hxy.bbs.mapper.PostMapper;
import cn.hxy.bbs.mapper.ReplyMapper;
import cn.hxy.bbs.mapper.VerifyMapper;
import cn.hxy.bbs.model.Verify;
import cn.hxy.bbs.service.VerifyService;

@Service
public class VerifyServiceImpl implements VerifyService{
	@Autowired
	private VerifyMapper verifyMapper;
	@Autowired
	private PostMapper postMapper;
	@Autowired
	private ReplyMapper replyMapper;
	@Override
	public int reportPost(int userId, int postId) {
		if(verifyMapper.getRecordByUserIdAndPostId(userId, postId)!=0){
			return 1;
		}	
		Verify verify = new Verify();
		verify.setUserId(userId);
		verify.setPostId(postId);
		verifyMapper.insertSelective(verify);
		return 0;
	}

	@Override
	public int reportReply(int userId, int replyId,int postId) {
		if(verifyMapper.getRecordByUserIdAndReplyId(userId, replyId)!=0){
			return 1;
		}	
		Verify verify = new Verify();
		verify.setUserId(userId);
		verify.setReplyId(replyId);
		verify.setPostId(postId);
		verifyMapper.insertSelective(verify);
		return 0;
	}

	@Override
	public List<VerifyPost> getAuditPostByParams(String params) {
		return verifyMapper.getAuditPostByParams(params);
	}

	@Override
	public int hideVerifyPostById(int id, int status,int postId) {
		postMapper.hidePostById(postId, status);
		return verifyMapper.hideVerifyById(id, status);
	}

	@Override
	public int deleteReportPost(int id) {
		return verifyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<VerifyReply> getAuditReplyByParams(String params) {
		return verifyMapper.getAuditReplyByParams(params);
	}

	@Override
	public int hideVerifyReplyById(int id, int status, int replyId) {
		replyMapper.hideReplyById(replyId, status);
		return verifyMapper.hideVerifyById(id, status);
	}

}
