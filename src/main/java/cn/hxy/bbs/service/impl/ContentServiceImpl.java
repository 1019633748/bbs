package cn.hxy.bbs.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.mapper.ContentMapper;
import cn.hxy.bbs.mapper.DownMapper;
import cn.hxy.bbs.mapper.ImageMapper;
import cn.hxy.bbs.mapper.UpMapper;
import cn.hxy.bbs.model.Content;
import cn.hxy.bbs.model.Down;
import cn.hxy.bbs.model.Image;
import cn.hxy.bbs.model.Up;
import cn.hxy.bbs.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentMapper contentMapper;
	
	@Autowired
	private UpMapper upMapper;
	
	@Autowired
	private DownMapper downMapper;
	
	@Autowired
	private ImageMapper imageMapper;
	
	
	@Override
	public List<Content> getContent(int id,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Content> list = contentMapper.selectByTitleId(id);
		for(Content content:list){
			content.setUris(imageMapper.getContentImageByContentId(content.getId()));
			content.setUri(imageMapper.getAvatarByUserId(content.getAuthorId()));
		}
		return list;
	}

	@Override
	public Content getContentById(Integer id) {
		return contentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int getAllUp(int id) {
		return contentMapper.getAllUp(id);
	}

	@Override
	public int getAllDown(int id) {
		return contentMapper.getAllDown(id);
	}

	@Override
	public int getAllUpAfterClick(Up up) {
		if(upMapper.getRecord(up)==1){
			upMapper.deleteRecord(up);
			contentMapper.updateUpIfExist(up.getContentId());
		}else{
			upMapper.insert(up);
			contentMapper.updateUpIfNotExist(up.getContentId());
		}
		return getAllUp(up.getContentId());
	}

	
	@Override
	public int getAllDownAfterClick(Down down) {
		if(downMapper.getRecord(down)==1){
			downMapper.deleteRecord(down);
			contentMapper.updateDownIfExist(down.getContentId());
		}else{
			downMapper.insert(down);
			contentMapper.updateDownIfNotExist(down.getContentId());
		}
		return contentMapper.getAllDown(down.getContentId());
	}

	@Override
	public int getTotalContentByTitleId(int id) {
		return contentMapper.getTotalContentByTitleId(id);
	}

	@Override
	public List<Content> getAllContentByUserId(int id,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return contentMapper.getAllByUserId(id);
	}

	@Override
	public void uploadContentImg(MultipartFile img, int contentId,int userId) throws IllegalStateException, IOException {
		String imgName=UUID.randomUUID()+img.getOriginalFilename();
		img.transferTo(new File("D:\\tool\\apache-tomcat-8.0.38\\webapps\\images\\content"+File.separator+imgName));
		Image image = new Image();
		image.setContentId(contentId);
		image.setUserId(userId);
		image.setUri(imgName);
		imageMapper.insert(image);
	}

	@Override
	public int addContent(Content content) {
		return contentMapper.insert(content);
	}

	@Override
	public List<Content> findContentByName(String name) {
		return contentMapper.findContentByName(name);
	}

	@Override
	public List<Content> getContent() {
		return contentMapper.getContent();
	}

}
