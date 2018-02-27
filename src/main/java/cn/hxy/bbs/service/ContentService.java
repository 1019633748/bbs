package cn.hxy.bbs.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
	
	int addContent(Content content);

	List<Content> getAllContentByUserId(int id, int pageNum, int pageSize);
	
	void uploadContentImg(MultipartFile img,int contentId,int userId)throws IllegalStateException, IOException;
	
	
}
