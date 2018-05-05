package cn.hxy.bbs.mapper;

import cn.hxy.bbs.model.UserAndRole;

public interface UserAndRoleMapper {
    int insert(UserAndRole record);

    int insertSelective(UserAndRole record);

}