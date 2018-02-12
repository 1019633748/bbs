package cn.hxy.bbs.service;

import java.util.List;

import cn.hxy.bbs.model.Title;

public interface TitleService {
	String getTitleNameById(int id);

	Title getTitleById(int id);

	List<Title> getAll(int id);

	List<Title> getTitleBySectionName(String name);
	
	List<Title> getTitlesByUserId(int id);

	int getTitleIdByName(String name);
}
