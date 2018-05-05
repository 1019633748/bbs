package cn.hxy.bbs.dto;

import cn.hxy.bbs.model.Reply;

public class ReplyDetail extends Reply{
	private String post;
	private String author;
	private String avatarUrl;
	private int top;
	private String topOrDown;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getTopOrDown() {
		return topOrDown;
	}
	public void setTopOrDown(String topOrDown) {
		this.topOrDown = topOrDown;
	}
	
}
