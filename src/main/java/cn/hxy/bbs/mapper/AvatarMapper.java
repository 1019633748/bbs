package cn.hxy.bbs.mapper;

import cn.hxy.bbs.model.Avatar;

public interface AvatarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Avatar record);

    int insertSelective(Avatar record);

    Avatar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Avatar record);

    int updateByPrimaryKey(Avatar record);
}