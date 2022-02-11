package com.hs.hsblog_backend.service;

import com.hs.hsblog_backend.entity.Comment;
import com.hs.hsblog_backend.entity.User;
import com.hs.hsblog_backend.model.dto.CommentDto;
import com.hs.hsblog_backend.model.vo.PageComment;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Hs
 * @Date 2022/2/10 19:45
 */
public interface CommentService {
    List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    List<PageComment> getPageCommentList(Integer page, Long blogId, Long parentCommentId);

    Comment getCommentById(Long id);

    void updateCommentPublishedById(Long commentId, Boolean published);

    void updateCommentNoticeById(Long commentId, Boolean notice);

    void deleteCommentById(Long commentId);

    void deleteCommentsByBlogId(Long blogId);

    void updateComment(Comment comment);

    int countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished);

    void saveComment(CommentDto comment);

    int judgeCommentEnabled(Integer page, Long blogId);
    List<Comment> getAllReplyComments(Long parentCommentId);
    void setAdminComment(CommentDto comment, HttpServletRequest request, User admin);
    void setVisitorComment(CommentDto comment, HttpServletRequest request);
    void setCommentRandomAvatar(CommentDto comment);

    void judgeSendMail(CommentDto comment, boolean isVisitorComment, Comment parentComment);

    void sendMailToParentComment(Comment parentComment, CommentDto comment);
    void sendMailToMe(CommentDto comment);
}
