package cn.hxy.bbs.service;

import cn.hxy.bbs.model.Top;

public interface TopService {
int getStatus(int userId,int replyId);

int clickTop(int userId,int replyId);

int clickDown(int userId,int replyId);

boolean isClick(int userId,int replyId);

int updateStatus(Top top);

int deleteByUserIdAndReplyId(int userId,int replyId);
}
