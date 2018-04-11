package cn.hxy.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.dto.SectionSize;
import cn.hxy.bbs.model.Section;

public interface SectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Section record);

    int insertSelective(Section record);

    Section selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);
    
    //获取版块集合
    List<SectionSize> findAllSection();
    
    List<SectionSize> findAllAdminSection();
    
    List<SectionSize> getAdminSectionByParam(@Param("params")String param);
    
    //通过id进入某板块
   // Section getSectionById(@Param("id") int id);
    
    Section getSectionByPostId(@Param("postId")int postId);
    
    int hideSectionById(@Param("id")int id,@Param("status")int status);
    
    Section getSectionByName(@Param("section") String section);
    
    int addSection(@Param("section")String section);
}