package cn.hxy.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.model.Image;

public interface ImageMapper {
    int insert(Image record);

    int insertSelective(Image record);
    
    List<String> getContentImageByContentId(int id);
    
    String getAvatarByUserId(int id);
    
    void updateAvatar(@Param("userId")int id,@Param("uri")String uri);
    
    void uploadContentImg(@Param("userId")int id,@Param("uri")String uri);
    
    void addAvatar(@Param("userId")int userId,@Param("uri")String uri);
}