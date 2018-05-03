package cn.hxy.bbs.service;

import java.util.List;

import cn.hxy.bbs.model.Avatar;

public interface AvatarService {
List<Avatar> findAll();

int banAvatarById(int id);
}
