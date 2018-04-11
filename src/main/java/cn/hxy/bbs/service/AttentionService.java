package cn.hxy.bbs.service;

public interface AttentionService {
int isAttention(int currentUserId,int targetUserId);
int attention(int currentUserId,int targetUserId);
int unfollow(int currentUserId,int targetUserId);
}
