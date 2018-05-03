package cn.hxy.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.model.Avatar;

public interface AvatarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Avatar record);

    int insertSelective(Avatar record);

    Avatar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Avatar record);

    int updateByPrimaryKey(Avatar record);
    
    int updateByUserId(@Param("userId")int userId,@Param("url")String url);
    
    List<Avatar> findAll();
    
    int banAvatarById(@Param("id") int id);
}