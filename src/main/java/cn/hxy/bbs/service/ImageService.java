package cn.hxy.bbs.service;

import java.util.List;

public interface ImageService {
List<String> getContentImageByContentId(int id);

String getAvatarByUserId(int id);

void updateAvatar(int id,String name);

void addAvatar(int userId,String uri);
}
