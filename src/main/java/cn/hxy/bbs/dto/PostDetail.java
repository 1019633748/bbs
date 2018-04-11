package cn.hxy.bbs.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hxy.bbs.model.Post;

public class PostDetail extends Post {
	private String author;
	 @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss") 
	private Date lastReply;
	private int replyAmount;
	private String section;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
	public int getReplyAmount() {
		return replyAmount;
	}

	public void setReplyAmount(int replyAmount) {
		this.replyAmount = replyAmount;
	}

	public Date getLastReply() {
		return lastReply;
	}

	public void setLastReply(Date lastReply) {
		this.lastReply = lastReply;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
}
