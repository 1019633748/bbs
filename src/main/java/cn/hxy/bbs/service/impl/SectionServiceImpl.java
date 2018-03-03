package cn.hxy.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.mapper.SectionMapper;
import cn.hxy.bbs.mapper.TitleMapper;
import cn.hxy.bbs.model.Section;
import cn.hxy.bbs.service.SectionService;

@Service
public class SectionServiceImpl implements SectionService {
	@Autowired
	private SectionMapper sectionMapper;
	
	@Autowired
	private TitleMapper titleMapper;
	
	@Override
	public List<Section> getSections(int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<Section> list = sectionMapper.getSections();
		for(Section section : list){
			PageHelper.startPage(1, 5);
			section.setTitles(titleMapper.getAll(section.getId()));
		}
		return list;
	}

	@Override
	public Section getSection(int id) {
		Section section = sectionMapper.selectByPrimaryKey(id);
		section.setTitles(titleMapper.getAll(id));
		return section;
	}

	@Override
	public Section getSection(String name) {
		Section section = sectionMapper.getSectionByName(name);
		section.setTitles(titleMapper.getAll(section.getId()));
		return section;
	}

	@Override
	public String getSectionNameById(int id) {
		return sectionMapper.getNameById(id);
	}

	@Override
	public List<Section> getSections() {
		return sectionMapper.getSections();
	}

	@Override
	public List<Section> findSectionByName(String name) {
		return sectionMapper.findSectionByName(name);
	}

}
