package com.example.blog.dao;

import com.example.blog.dto.SearchBlogsDTO;
import com.example.blog.pojo.Blog;
import com.example.blog.vo.BlogsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {

    Blog getBlogById(Long id);

    List<BlogsVO> getAllBlog();

    List<BlogsVO> getAllBlog(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    List<BlogsVO> getAllBlogQuery(SearchBlogsDTO searchBlogsDTO);

    List<BlogsVO> getAllBlogSearch(String search, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

//    List<BlogsVO> getBlogByNameAndContent(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);//TODO:此方法需接入elasticsearch搜索引擎,暂时使用like查找来替换

    //查询一个typeId下的所有blog
    List<BlogsVO> getAllBlogByTypeId(Long id, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    //得到所有推荐的blog
    List<BlogsVO> getAllRecommendBlog(Integer size);

    Long countBlog();

    //通过日期查询获取一个年月的列表
    List<Blog> listBlogByDate(String yearAndMonth);

    //通过查询到的日期获得每个日期对应的列表
    List<String> getBlogDate();

    int saveBlog(Blog blog);

    int deleteBlogById(Long id);

    int updateBlog(Blog blog);

    void updateBlogView(Long id);

}
