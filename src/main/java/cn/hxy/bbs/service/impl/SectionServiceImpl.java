package cn.hxy.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.dto.SectionSize;
import cn.hxy.bbs.mapper.SectionMapper;
import cn.hxy.bbs.model.Section;
import cn.hxy.bbs.service.SectionService;

@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionMapper sectionMapper;
	
	@Override
	public List<SectionSize> findAllSection() {
		return sectionMapper.findAllSection() ;
	}

	@Override
	public Section getSectionById(int id) {
		return sectionMapper.selectByPrimaryKey(id);
	}

	
	

	@Override
	public int deleteSection(int sectionId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Section getSectionByPostId(int postId) {
		return sectionMapper.getSectionByPostId(postId);
	}

	@Override
	public int hideSectionById(int id,int status) {
		return sectionMapper.hideSectionById(id,status);
	}

	@Override
	public List<SectionSize> findAllAdminSection() {
		return sectionMapper.findAllAdminSection();
	}

	@Override
	public Section getSectionByName(String section) {
		return sectionMapper.getSectionByName(section);
	}

	@Override
	public int addSection(String section) {
		return sectionMapper.addSection(section);
	}

	@Override
	public List<SectionSize> getAdminSectionByParam(String param) {
		return sectionMapper.getAdminSectionByParam(param);
	}

}
