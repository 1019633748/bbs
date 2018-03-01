package cn.hxy.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.mapper.FriendMapper;
import cn.hxy.bbs.mapper.ImageMapper;
import cn.hxy.bbs.mapper.UserMapper;
import cn.hxy.bbs.model.Friend;
import cn.hxy.bbs.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendMapper friendMapper;
	
	@Autowired
	private ImageMapper imageMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int getAttentionByUserId(int id) {
		return friendMapper.getAttention(id);
	}

	@Override
	public int getFansByUserId(int id) {
		// TODO Auto-generated method stub
		return friendMapper.getFans(id);
	}

	@Override
	public Map<String,String> getAttentionIdList(int id) {
		Map<String,String> userMap = new HashMap<String,String>(); 
		List<Integer> idList = friendMapper.getAttentionIdList(id);
		for(int attentionId:idList){
			userMap.put(userMapper.getUsernameById(attentionId),imageMapper.getAvatarByUserId(attentionId));
		}
		return userMap;
	}

	@Override
	public Map<String,String> getFansIdList(int id) {
		Map<String,String> userMap = new HashMap<String,String>(); 
		List<Integer> idList = friendMapper.getFansIdList(id);
		for(int fansId:idList){
			userMap.put(userMapper.getUsernameById(fansId),imageMapper.getAvatarByUserId(fansId));
		}
		return userMap;
	}

	@Override
	public boolean isAttention(int userId, int targetId) {
		return friendMapper.isAttention(userId, targetId);
	}

	@Override
	public int addAttention(int userId, int friendId) {
		Friend friend = new Friend();
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		return friendMapper.insert(friend);
	}

	@Override
	public int removeAttention(int userId, int friendId) {
		return friendMapper.removeAttention(userId, friendId);
	}

}
