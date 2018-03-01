package cn.hxy.bbs.service;

import java.util.Map;

public interface FriendService {
	int getAttentionByUserId(int id);

	int getFansByUserId(int id);

	Map<String,String> getAttentionIdList(int id);

	Map<String,String>getFansIdList(int id);
	
	boolean isAttention(int userId,int targetId);
	
	int addAttention(int userId,int friendId);
	
	int removeAttention(int userId,int friendId);
}
