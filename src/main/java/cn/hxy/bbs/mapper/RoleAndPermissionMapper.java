package cn.hxy.bbs.mapper;

import cn.hxy.bbs.model.RoleAndPermission;

public interface RoleAndPermissionMapper {
    int insert(RoleAndPermission record);

    int insertSelective(RoleAndPermission record);
}