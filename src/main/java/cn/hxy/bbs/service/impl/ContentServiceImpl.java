package cn.hxy.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.mapper.ContentMapper;
import cn.hxy.bbs.mapper.DownMapper;
import cn.hxy.bbs.mapper.ImageMapper;
import cn.hxy.bbs.mapper.UpMapper;
import cn.hxy.bbs.model.Content;
import cn.hxy.bbs.model.Down;
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

}
