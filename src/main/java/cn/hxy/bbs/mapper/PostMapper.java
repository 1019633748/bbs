package cn.hxy.bbs.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.hxy.bbs.dto.HotPost;
import cn.hxy.bbs.dto.PostDetail;
import cn.hxy.bbs.model.Post;

public interface PostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer id);
    
    Post getAdviceById(Integer id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);
    
    List<PostDetail> getPostDetailBySectionId(@Param("sectionId")int sectionId);
    
    List<HotPost> findHotPost();
    
    List<PostDetail> getHotPostDetailBySectionId(@Param("sectionId")int sectionId);
    
    List<PostDetail> getLastPostDetailBySectionId(@Param("sectionId")int sectionId);
    
    List<PostDetail> findAllAdminPost();
    
    int hidePostById(@Param("id")int id,@Param("status") int status);
    
    List<PostDetail> getPostDetailByName(@Param("params")String params);
    
    List<PostDetail> getPostByUserId(@Param("userId")int userId,@Param("params")String params);
    
    @SuppressWarnings("rawtypes")
	List<Map> getPostCreate();
}