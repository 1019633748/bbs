package cn.hxy.bbs.mapper;

import cn.hxy.bbs.model.Down;

public interface DownMapper {
    int insert(Down record);

    int insertSelective(Down record);
    
    int getRecord(Down record);
    
    int deleteRecord (Down record);
    
}