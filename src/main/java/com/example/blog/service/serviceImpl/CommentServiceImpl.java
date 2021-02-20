package com.example.blog.service.serviceImpl;

import com.example.blog.dao.CommentDao;
import com.example.blog.pojo.Comment;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    @Transactional
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //查询出父节点
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId);
        for (Comment comment : comments) {
            Long id = comment.getId();
            String parentNickname1 = comment.getNickName();
            //查询出所有的子评论
            List<Comment> childComments = commentDao.findByBlogIdParentIdNotNull(blogId, id);
            combineChildren(blogId, childComments, parentNickname1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        int i = 1;
        return comments;
    }

    /**
     * BlogId表示该博客的id,childComments是顶级评论的的一级评论，parentNickname1为顶级评论的名称
     *
     * @param blogId
     * @param childComments
     * @param parentNickname1
     */
    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
        //判断是否有一级评论
        if (childComments.size() > 0) {
            for (Comment childComment : childComments) {
                String parentNickname = childComment.getNickName();
                childComment.setParentNickName(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                //查询二级评论
                recursively(blogId, childId, parentNickname);
            }
        }
    }

    /**
     * param1 is BlogId,param2 is ID of two-level comment,param3 is parent name of child ID
     *
     * @param blogId
     * @param childId
     * @param parentNickname1
     */
    private void recursively(Long blogId, Long childId, String parentNickname1) {
        //根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentDao.findByBlogIdAndReplayId(blogId, childId);
        if (replayComments.size() > 0) {
            for (Comment replayComment : replayComments) {
                String parentNickname = replayComment.getNickName();
                replayComment.setParentNickName(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(blogId, replayId, parentNickname);
            }
        }
    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        Long parentCommentId = comment.getParentCommentId();
        if (parentCommentId == -1) {
            comment.setParentCommentId(null);
        }
        comment.setCreateTime(new Date());
        return commentDao.saveComment(comment);
    }

    @Override
    public int deleteComment(Long id) {
        return commentDao.deleteComment(id);
    }
}
