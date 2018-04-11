package cn.hxy.bbs.mapper;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.model.Role;
import cn.hxy.bbs.model.UserAndRole;

public interface UserAndRoleMapper {
    int insert(UserAndRole record);

    int insertSelective(UserAndRole record);

}