package cn.hxy.bbs.model;

import java.util.List;

public class Section {
    private Integer id;

    private String sectionName;
    
    private List<Title> titles;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName == null ? null : sectionName.trim();
    }

	public List<Title> getTitles() {
		return titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", sectionName=" + sectionName + ", titles=" + titles + "]";
	}

	
    
}