package com.example.blog.service;

import com.example.blog.dto.SearchBlogsDTO;
import com.example.blog.pojo.Blog;
import com.example.blog.vo.BlogsVO;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    List<BlogsVO> listBlog();

    List<BlogsVO> listBlogByBlogQuery(SearchBlogsDTO blogQuery);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    //用户界面service

    List<BlogsVO> listRecommendBlog(Integer size);

    List<BlogsVO> listBlog(int pageNum, int pageSize);

    List<BlogsVO> listBlogByBlogSearch(String query, int pageNum, int pageSize);//TODO:此方法需接入elasticsearch搜索殷勤,暂时用like查找代替

    // List<BlogsVO> listBlogByBlogQuery(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);//TODO:此方法需接入elasticsearch搜索引擎

    List<BlogsVO> listBlogByTypeId(Long id, int pageNum, int pageSize);

    Blog getBlogAndConvert(Long id);

    Long countBlog();

    Map<String, List<Blog>> archiveBlog();

    void updateBlogView(Long id);

}
