package cn.hxy.bbs.mapper;

import java.util.List;

import cn.hxy.bbs.model.Image;

public interface ImageMapper {
    int insert(Image record);

    int insertSelective(Image record);
    
    List<String> getContentImageByContentId(int id);
    
    String getAvatarByUserId(int id);
}