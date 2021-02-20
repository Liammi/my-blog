package com.example.blog;

import com.example.blog.dao.BlogDao;
import com.example.blog.service.BlogService;
import com.example.blog.service.CommentService;
import com.example.blog.service.TypeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
/*@SpringBootTest(classes = Application.class)
@MapperScan(value = "com.example.blog.dao")
@ComponentScan(value = "com.example.blog.service")*/
class ApplicationTests {

	@Autowired
	BlogDao blogDao;

	@Autowired
	private BlogService blogService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private CommentService commentService;

	@Test
	void TypeTest() {

		System.out.println(typeService.listTypeAndBlog());

		System.out.println(typeService.getTypeByName("JVM"));

		System.out.println(typeService.getType(1L));

	}

	@Test
	void BlogServiceTest() {

		/*System.out.println(blogService.listBlog(4,1));
		System.out.println(blogService.listBlog(4,1).getClass());*/

//		System.out.println(blogService.getBlog(1L));

		/*		System.out.println(blogService.listBlogByBlogQuery(new SearchBlogsDTO("反",1L,false)));*/

		/*		System.out.println(blogDao.getAllBlogByTypeId(3L,1,10));*/

		/*SearchBlogsDTO blogsDTO = new SearchBlogsDTO();
		blogsDTO.setTitle("%java%");
		System.out.println(blogDao.getAllBlogQuery(blogsDTO));*/

//		System.out.println(blogDao.countBlog());

//		System.out.println(blogDao.getBlogDate());

		System.out.println(blogDao.listBlogByDate("2020/04"));
	}

	@Test
	void commentServiceTest() {
		System.out.println(commentService.listCommentByBlogId(46L));
	}

}
