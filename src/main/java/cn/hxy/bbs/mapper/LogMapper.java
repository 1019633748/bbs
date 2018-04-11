package cn.hxy.bbs.mapper;

import cn.hxy.bbs.model.Log;

public interface LogMapper {
    int insert(Log record);

    int insertSelective(Log record);
}