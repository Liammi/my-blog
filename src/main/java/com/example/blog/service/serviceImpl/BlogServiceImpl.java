package com.example.blog.service.serviceImpl;

import com.example.blog.dao.BlogDao;
import com.example.blog.dto.SearchBlogsDTO;
import com.example.blog.exception.NotFoundException;
import com.example.blog.pojo.Blog;
import com.example.blog.service.BlogService;
import com.example.blog.util.MarkDownUtils;
import com.example.blog.vo.BlogsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog getBlog(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public List<BlogsVO> listBlogByBlogQuery(SearchBlogsDTO blogQuery) {
        StringBuffer name = new StringBuffer("%");
        name.append(blogQuery.getTitle());
        name.append("%");
        blogQuery.setTitle(name.toString());
        return blogDao.getAllBlogQuery(blogQuery);
    }

    @Override
    public List<BlogsVO> listBlog() {
        return blogDao.getAllBlog();
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogDao.saveBlog(blog);
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        Blog blog1 = blogDao.getBlogById(blog.getId());
        if (blog1 == null) {
            throw new NotFoundException("该博客不存在");
        }
        blog.setUpdateTime(new Date());
        return blogDao.updateBlog(blog);
    }

    @Transactional
    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlogById(id);
    }

    @Override
    public List<BlogsVO> listRecommendBlog(Integer size) {
        return blogDao.getAllRecommendBlog(size);
    }

    @Override
    public List<BlogsVO> listBlog(int pageNum, int pageSize) {
        return blogDao.getAllBlog(pageNum, pageSize);
    }

    @Override
    public List<BlogsVO> listBlogByBlogSearch(String query, int pageNum, int pageSize) {
        String name = "%" + query +
                "%";
        return blogDao.getAllBlogSearch(name, pageNum, pageSize);
    }

    @Override
    public List<BlogsVO> listBlogByTypeId(Long id, int pageNum, int pageSize) {
        return blogDao.getAllBlogByTypeId(id, pageNum, pageSize);
    }

    @Override
    public Blog getBlogAndConvert(Long id) {
        Blog blog = blogDao.getBlogById(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkDownUtils.markdownToHtmlExtensions(content));
        return blog;
    }

    @Override
    public Long countBlog() {
        return blogDao.countBlog();
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> yearAndMonths = blogDao.getBlogDate();
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String yearAndMonth : yearAndMonths) {
            map.put(yearAndMonth, blogDao.listBlogByDate(yearAndMonth));
        }
        return map;
    }

    @Transactional
    @Override
    public void updateBlogView(Long id) {
        blogDao.updateBlogView(id);
    }

}
