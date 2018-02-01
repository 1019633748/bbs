package cn.hxy.bbs.mapper;

import java.util.List;

import cn.hxy.bbs.model.Section;

public interface SectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Section record);

    int insertSelective(Section record);
    
    Section selectByPrimaryKey(Integer id);
    
    Section getSectionByName(String name);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);
    
    List<Section> getSections();
}