package cn.hxy.bbs.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Title {
	private Integer id;

	private String name;

	private String author;

	@JsonFormat(timezone = "GMT+8", pattern = "yy年M月d日H时")
	private Date createDate;

	@JsonFormat(timezone = "GMT+8", pattern = "yy年M月d日H时")
	private Date updateDate;

	private Integer sectionId;

	private String firstFloor;

	private String sectionName;
	
	private int userId;

	List<String> uris;

	private String uri;

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

	
	public List<String> getUris() {
		return uris;
	}

	public void setUris(List<String> uris) {
		this.uris = uris;
	}

	@Override
	public String toString() {
		return "Title [id=" + id + ", name=" + name + ", author=" + author + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", sectionId=" + sectionId + ", firstFloor=" + firstFloor + "]";
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}