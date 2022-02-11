package com.hs.hsblog_backend.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.annotation.OperationAnnotation;
import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.entity.Comment;
import com.hs.hsblog_backend.model.vo.BlogIdAndTitle;
import com.hs.hsblog_backend.service.BlogService;
import com.hs.hsblog_backend.service.CommentService;
import com.hs.hsblog_backend.util.Result;
import com.hs.hsblog_backend.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hs
 * @Date 2022/2/11 17:29
 */
@RestController
@RequestMapping("admin")
public class CommentAdminController {
    @Autowired
    CommentService commentService;
    @Autowired
    BlogService blogService;

    /**
     * 按页面和博客id分页查询评论List
     *
     * @param page     要查询的页面(博客文章or关于我...)
     * @param blogId   如果是博客文章页面 需要提供博客id
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return
     */
    @GetMapping("/comments")
    public Result comments(@RequestParam(defaultValue = "") Integer page,
                           @RequestParam(defaultValue = "") Long blogId,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Comment> comments = commentService.getListByPageAndParentCommentId(page, blogId, (long) -1);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        return Result.success(pageInfo);
    }

    /**
     * 获取所有博客id和title 供评论分类的选择
     *
     * @return
     */
    @GetMapping("/blogIdAndTitle")
    public Result blogIdAndTitle() {
        List<BlogIdAndTitle> blogs = blogService.getIdAndTitleList();
        return Result.success(blogs);
    }

    /**
     * 更新评论公开状态
     *
     * @param id        评论id
     * @param published 是否公开
     * @return
     */
    @OperationAnnotation("更新评论公开状态")
    @PutMapping("/comment/published")
    public Result updatePublished(@RequestParam Long id, @RequestParam Boolean published) {
        commentService.updateCommentPublishedById(id, published);
        return Result.success();
    }

    /**
     * 更新评论接收邮件提醒状态
     *
     * @param id     评论id
     * @param notice 是否接收提醒
     * @return
     */
    @OperationAnnotation("更新评论邮件提醒状态")
    @PutMapping("/comment/notice")
    public Result updateNotice(@RequestParam Long id, @RequestParam Boolean notice) {
        commentService.updateCommentNoticeById(id, notice);
        return Result.success();
    }

    /**
     * 按id删除该评论及其所有子评论
     *
     * @param id 评论id
     * @return
     */
    @OperationAnnotation("删除评论")
    @DeleteMapping("/comment")
    public Result delete(@RequestParam Long id) {
        commentService.deleteCommentById(id);
        return Result.success();
    }

    /**
     * 修改评论
     *
     * @param comment 评论实体
     * @return
     */
    @OperationAnnotation("修改评论")
    @PutMapping("/comment")
    public Result updateComment(@RequestBody Comment comment) {
        if (StringUtils.isEmpty(comment.getNickname(), comment.getAvatar(), comment.getEmail(), comment.getIp(), comment.getContent())) {
            return Result.fail("参数有误");
        }
        commentService.updateComment(comment);
        return Result.success();
    }
}
