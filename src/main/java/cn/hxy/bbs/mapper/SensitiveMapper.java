package cn.hxy.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.model.Sensitive;

public interface SensitiveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sensitive record);

    int insertSelective(Sensitive record);

    Sensitive selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sensitive record);

    int updateByPrimaryKey(Sensitive record);
    
    List<Sensitive> getSensitiveByWord(@Param("params")String params);
    
    int hideSensitiveById(@Param("id")int id, @Param("status")int status);
    
    Sensitive getSensitiveByName(@Param("sensitive") String sensitive);
    
    int addSensitive(@Param("sensitive")String sensitive);
}