package cn.hxy.bbs.service;

import java.util.List;

import cn.hxy.bbs.model.Section;

public interface SectionService {
	public List<Section> getSections(int pageNum, int pageSize);

	public Section getSection(int id);

	public Section getSection(String name);

	String getSectionNameById(int id);
}
