package cn.hxy.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.mapper.ImageMapper;
import cn.hxy.bbs.service.ImageService;
@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	private ImageMapper imageMapper;

	@Override
	public List<String> getContentImageByContentId(int id) {
		return imageMapper.getContentImageByContentId(id);
	}

}
