package cn.hxy.bbs.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.dto.HotPost;
import cn.hxy.bbs.dto.PostDetail;
import cn.hxy.bbs.mapper.PostMapper;
import cn.hxy.bbs.model.Post;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostMapper postMapper;

	@Override
	public List<PostDetail> getPostDetailBySectionId(int sectionId) {
		return postMapper.getPostDetailBySectionId(sectionId);
	}

	@Override
	public int addPost(Post post, HttpSession session) {
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		post.setUserId(user.getId());
		return postMapper.insertSelective(post);
	}
	
	@Override
	public int addAdviceTitle(Post post, HttpSession session) {
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		post.setUserId(user.getId());
		post.setStatus((byte)9);
		post.setSectionId(5);
		return postMapper.insertSelective(post);
	}
	
	@Override
	public List<HotPost> findHotPost() {
		return postMapper.findHotPost();
	}

	

	@Override
	public List<Post> getCheckPost() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<PostDetail> getHotPostDetailBySectionId(int sectionId) {
		return postMapper.getHotPostDetailBySectionId(sectionId);
	}

	@Override
	public List<PostDetail> getLastPostDetailBySectionId(int sectionId) {
		return postMapper.getLastPostDetailBySectionId(sectionId);
	}

	@Override
	public Post getPostById(int id) {
		return postMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PostDetail> findAllAdminPost() {
		return postMapper.findAllAdminPost();
	}

	@Override
	public int hidePostById(int id, int status) {
		return postMapper.hidePostById(id, status);
	}

	@Override
	public List<PostDetail> getAdminPostDetailByName(String params) {
		return postMapper.getPostDetailByName(params);
	}

	@Override
	public List<PostDetail> getPostByUserId(int userId, String params) {
		return postMapper.getPostByUserId(userId, params);
	}



}
