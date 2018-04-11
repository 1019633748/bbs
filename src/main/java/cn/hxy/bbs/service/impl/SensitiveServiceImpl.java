package cn.hxy.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.mapper.SensitiveMapper;
import cn.hxy.bbs.model.Sensitive;
import cn.hxy.bbs.service.SensitiveService;
@Service
public class SensitiveServiceImpl implements SensitiveService{
	@Autowired
	private SensitiveMapper sensitiveMapper;
	@Override
	public List<Sensitive> getSensitiveByWord(String params) {
		return sensitiveMapper.getSensitiveByWord(params);
	}
	@Override
	public int hideSensitiveById(int id, int status) {
		return sensitiveMapper.hideSensitiveById(id, status);
	}
	public void addSensitive(String word) {
		sensitiveMapper.addSensitive(word);
	}
	public Sensitive getSensitiveByName(String sensitive) {
		return sensitiveMapper.getSensitiveByName(sensitive);
	}

}
