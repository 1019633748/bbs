package cn.hxy.bbs.service;

import java.util.List;

public interface ImageService {
List<String> getContentImageByContentId(int id);

String getAvatarByUserId(int id);
}
