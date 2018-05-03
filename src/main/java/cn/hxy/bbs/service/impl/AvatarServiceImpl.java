package cn.hxy.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.mapper.AvatarMapper;
import cn.hxy.bbs.model.Avatar;
import cn.hxy.bbs.service.AvatarService;

@Service
public class AvatarServiceImpl implements AvatarService{
	@Autowired
private AvatarMapper avatarMapper;

@Override
public List<Avatar> findAll() {
	return avatarMapper.findAll();
}

@Override
public int banAvatarById(int id) {
	return avatarMapper.banAvatarById(id);
}
}
