package cn.hxy.bbs.service;

import java.util.List;

import cn.hxy.bbs.dto.VerifyPost;
import cn.hxy.bbs.dto.VerifyReply;

public interface VerifyService {
int reportPost(int userId,int postId);
int reportReply(int userId,int replyId,int postId);
List<VerifyPost> getAuditPostByParams(String params);
List<VerifyReply> getAuditReplyByParams(String params);
int hideVerifyPostById(int id,int status,int postId);
int hideVerifyReplyById(int id,int status,int replyId);
int deleteReportPost(int id);
}
