package cn.hxy.bbs.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.dto.HotPost;
import cn.hxy.bbs.dto.PostDetail;
import cn.hxy.bbs.model.Post;

public interface PostService {
	
	List<PostDetail> getPostDetailBySectionId(int sectionId);

	int addPost(Post post, HttpSession session);
	
	int addAdviceTitle(Post post, HttpSession session);
	
	List<HotPost> findHotPost();
	
	List<Post> getCheckPost();

	
	List<PostDetail> getHotPostDetailBySectionId(int sectionId);
	
	List<PostDetail> getLastPostDetailBySectionId(int sectionId);
	
	List<PostDetail> getAdminPostDetailByName(String params);
	
	Post getPostById(int id);
	
	Post getAdviceById(int id);
	
	List<PostDetail> findAllAdminPost();
	
	int hidePostById(int id,int status);
	
	List<PostDetail> getPostByUserId(int userId,String params);
	
	List<Map> getPostCreate();
}
