package cn.hxy.bbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.mapper.TopMapper;
import cn.hxy.bbs.model.Top;
import cn.hxy.bbs.service.TopService;

@Service
public class TopServiceImpl implements TopService {

	@Autowired
	private TopMapper topMapper;

	@Override
	public int clickTop(int userId, int replyId) {
		Top top = new Top();
		if (isClick(userId, replyId)) {
			top.setUserId(userId);
			top.setReplyId(replyId);
			if (getStatus(userId, replyId) != 0) {
				top.setStatus(0);
				updateStatus(top);
			} else {
				topMapper.deleteByUserIdAndReplyId(userId, replyId);
			}	
		} else {
			top.setUserId(userId);
			top.setReplyId(replyId);
			top.setStatus(0);
			topMapper.insert(top);
		}
		return topMapper.findTop(replyId)-topMapper.findDown(replyId);
	}

	@Override
	public int clickDown(int userId, int replyId) {
		Top top = new Top();
		if (isClick(userId, replyId)) {
			top.setUserId(userId);
			top.setReplyId(replyId);
			if (getStatus(userId, replyId) != 1) {
				top.setStatus(1);
				updateStatus(top);
			} else {
				topMapper.deleteByUserIdAndReplyId(userId, replyId);
			}	
		} else {
			top.setUserId(userId);
			top.setReplyId(replyId);
			top.setStatus(1);
			topMapper.insert(top);
		}
		return topMapper.findTop(replyId)-topMapper.findDown(replyId);
	}
	
	
	@Override
	public boolean isClick(int userId, int replyId) {
		return topMapper.isClick(userId, replyId);
	}

	@Override
	public int updateStatus(Top top) {
		return topMapper.updateStatus(top);
	}

	@Override
	public int getStatus(int userId, int replyId) {
		return topMapper.getStatus(userId, replyId);
	}

	@Override
	public int deleteByUserIdAndReplyId(int userId, int replyId) {
		return topMapper.deleteByUserIdAndReplyId(userId, replyId);
	}

	

}
