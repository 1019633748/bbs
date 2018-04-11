package cn.hxy.bbs.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Reply {
    private Integer id;
    
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createDate;

    private Byte status;

    private Integer postId;

    private Integer userId;

    private String reply;
    
    private String text;
    
    private int no;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
    	String str = reply.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "");
    	setText(str);
        this.reply = reply == null ? null : reply.trim();
    }

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}