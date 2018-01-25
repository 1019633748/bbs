package cn.hxy.bbs.service;

import java.util.List;

import cn.hxy.bbs.model.Content;
import cn.hxy.bbs.model.Down;
import cn.hxy.bbs.model.Up;

public interface ContentService {
	List<Content> getContent(int id, int pageNum, int pageSize);

	Content getContentById(Integer id);

	int getAllUp(int id);

	int getAllDown(int id);

	int getAllUpAfterClick(Up up);

	int getAllDownAfterClick(Down down);
	
	int getTotalContentByTitleId(int id	);
}
