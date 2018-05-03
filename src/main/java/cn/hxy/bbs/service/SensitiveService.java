package cn.hxy.bbs.service;

import java.util.List;

import cn.hxy.bbs.model.Sensitive;

public interface SensitiveService {
	List<Sensitive> getSensitiveByWord(String params);

	int hideSensitiveById(int id, int status);

	List<String> getAllWord();
}
