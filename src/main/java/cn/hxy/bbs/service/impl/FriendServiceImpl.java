package cn.hxy.bbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.mapper.FriendMapper;
import cn.hxy.bbs.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendMapper friendMapper;
	
	@Override
	public int getAttentionByUserId(int id) {
		return friendMapper.getAttention(id);
	}

	@Override
	public int getFansByUserId(int id) {
		// TODO Auto-generated method stub
		return friendMapper.getFans(id);
	}

}
