package cn.hxy.bbs.dto;

import java.util.Date;

import cn.hxy.bbs.model.Post;

public class HotPost extends Post {
	private String author;
	
	private int replyAmount;
	
	private Date lastReply;
	
	private String section;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	

	public Date getLastReply() {
		return lastReply;
	}

	public void setLastReply(Date lastReply) {
		this.lastReply = lastReply;
	}

	public int getReplyAmount() {
		return replyAmount;
	}

	public void setReplyAmount(int replyAmount) {
		this.replyAmount = replyAmount;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	
}
