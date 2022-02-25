package com.hs.repository;

import com.hs.entity.Comment;
import com.hs.model.dto.CommentDto;
import com.hs.model.vo.PageComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.hs.entity.Comment
 */
@Mapper
public interface CommentMapper {
    List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    List<Comment> getListByParentCommentId(Long parentCommentId);

    List<PageComment> getPageCommentListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    Comment getCommentById(Long id);

    int updateCommentPublishedById(Long commentId, Boolean published);

    int updateCommentNoticeById(Long commentId, Boolean notice);

    int deleteCommentById(Long commentId);

    int deleteCommentsByBlogId(Long blogId);

    int updateComment(Comment comment);

    int countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished);

    int countComment();

    int saveComment(CommentDto comment);
}




