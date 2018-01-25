package cn.hxy.bbs.model;

import java.util.Date;

public class Title {
    private Integer id;

    private String name;

    private String author;

    private Date createDate;

    private Date updateDate;

    private Integer sectionId;
    
    private String firstFloor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

	public String getFirstFloor() {
		return firstFloor;
	}

	public void setFirstFloor(String firstFloor) {
		this.firstFloor = firstFloor;
	}

	@Override
	public String toString() {
		return "Title [id=" + id + ", name=" + name + ", author=" + author + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", sectionId=" + sectionId + ", firstFloor=" + firstFloor + "]";
	}

	
    
}