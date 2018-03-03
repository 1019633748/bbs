package cn.hxy.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.model.Title;

public interface TitleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Title record);

    int insertSelective(Title record);

    Title selectByPrimaryKey(Integer id);
    
    String getTitleNameById(int id);

    int updateByPrimaryKeySelective(Title record);

    int updateByPrimaryKey(Title record);
    
    int getTitleIdByName(String name);
    
    List<Title> getAll(int id);
    
    List<Title> getTitleBySectionName(String name);
    
    List <Title> getTitlesByUserId(int id);
    
    List <Title> getTitles();
    
    List <Title> findTitleByName(@Param("name")String name);
}