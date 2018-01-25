package cn.hxy.bbs.mapper;

import java.util.List;

import cn.hxy.bbs.model.Content;

public interface ContentMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Content record);

	int insertSelective(Content record);

	Content selectByPrimaryKey(Integer id);

	List<Content> selectByTitleId(Integer id);

	int updateByPrimaryKeySelective(Content record);

	int updateByPrimaryKeyWithBLOBs(Content record);

	int updateByPrimaryKey(Content record);

	int getAllUp(int id);

	int getAllDown(int id);

	int updateUpIfExist(int id);

	int updateUpIfNotExist(int id);

	int updateDownIfExist(int id);

	int updateDownIfNotExist(int id);
	
	int getTotalContentByTitleId(int id);
}