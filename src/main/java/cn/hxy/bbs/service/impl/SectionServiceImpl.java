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
		PageHelper.startPage(1, 2);
		List<Section> list = sectionMapper.getSections();
		for(Section section : list){
			PageHelper.startPage(1, 2);
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

}
