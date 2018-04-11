package cn.hxy.bbs.service;

import java.util.List;

import cn.hxy.bbs.dto.SectionSize;
import cn.hxy.bbs.model.Section;

public interface SectionService {

	List<SectionSize> findAllSection();
	
	List<SectionSize> findAllAdminSection();
	
	Section getSectionById(int id);
	
	int deleteSection(int sectionId);
	
	Section getSectionByPostId(int postId);
	
	int hideSectionById(int id,int status);
	
	Section getSectionByName(String section);
	
	int addSection(String  section);
	
	List<SectionSize> getAdminSectionByParam(String param);
}
