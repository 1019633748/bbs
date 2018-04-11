package cn.hxy.bbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.mapper.FriendMapper;
import cn.hxy.bbs.model.Friend;
import cn.hxy.bbs.service.AttentionService;

@Service
public class AttentionServiceImpl implements AttentionService{
	@Autowired
	private FriendMapper friendMapper;
	@Override
	public int isAttention(int currentUserId, int targetUserId) {
		return friendMapper.isAttention(currentUserId, targetUserId);
	}

	@Override
	public int attention(int currentUserId, int targetUserId) {
		Friend f = new Friend();
		f.setUserId(currentUserId);
		f.setFriendId(targetUserId);
		return friendMapper.insert(f);
	}

	@Override
	public int unfollow(int currentUserId, int targetUserId) {
		return friendMapper.unfollow(currentUserId, targetUserId);
	}

}
