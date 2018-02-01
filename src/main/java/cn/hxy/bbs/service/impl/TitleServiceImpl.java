package cn.hxy.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.mapper.TitleMapper;
import cn.hxy.bbs.model.Title;
import cn.hxy.bbs.service.TitleService;
@Service
public class TitleServiceImpl implements TitleService {
	@Autowired
	private TitleMapper titleMapper;
	@Override
	public String getTitleNameById(int id) {
		return titleMapper.getTitleNameById(id);
	}
	@Override
	public Title getTitleById(int id) {
		return titleMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<Title> getAll(int id) {
		return titleMapper.getAll(id);
	}
	@Override
	public List<Title> getTitleBySectionName(String name) {
		return titleMapper.getTitleBySectionName(name);
	}
	@Override
	public int getTitleIdByName(String name) {
		return titleMapper.getTitleIdByName(name);
	}

}
