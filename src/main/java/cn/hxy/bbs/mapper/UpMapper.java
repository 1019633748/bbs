package cn.hxy.bbs.mapper;

import cn.hxy.bbs.model.Up;

public interface UpMapper {
    int insert(Up record);

    int insertSelective(Up record);
    
    int getRecord(Up up);
    
    int deleteRecord (Up up);
}