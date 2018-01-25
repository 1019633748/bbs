package cn.hxy.bbs.model;

import java.util.Date;
import java.util.List;

public class Content {
    private Integer id;

    private Integer authorId;

    private Integer titleId;

    private Integer up;

    private Integer down;

    private Date createDate;

    private String content;
    
    private String authorName;
    
    List <String> uris;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public Integer getDown() {
        return down;
    }

    public void setDown(Integer down) {
        this.down = down;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	
	
	public List<String> getUris() {
		return uris;
	}

	public void setUris(List<String> uris) {
		this.uris = uris;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", authorId=" + authorId + ", titleId=" + titleId + ", up=" + up + ", down=" + down
				+ ", createDate=" + createDate + ", content=" + content + ", authorName=" + authorName + "]";
	}

	
    
}