package com.hs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.annotation.AccessLimit;
import com.hs.entity.User;
import com.hs.entity.Comment;
import com.hs.model.dto.CommentDto;
import com.hs.model.vo.PageComment;
import com.hs.service.CommentService;
import com.hs.service.impl.UserServiceImpl;
import com.hs.util.JwtUtil;
import com.hs.util.Result;
import com.hs.util.StringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hs
 * @Date 2022/2/10 17:23
 */
@RestController
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserServiceImpl userService;

    /**
     * 根据页面分页查询评论列表
     *
     * @param page     页面分类（0普通文章，1关于我...）
     * @param blogId   如果page==0，需要博客id参数
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @param jwt      若文章受密码保护，需要获取访问Token
     * @return
     */
    @GetMapping("/comments")
    public Result comments(@RequestParam Integer page,
                           @RequestParam(defaultValue = "") Long blogId,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestHeader(value = "Authorization", defaultValue = "") String jwt) {
        int judgeResult = commentService.judgeCommentEnabled(page, blogId);
        if (judgeResult == 2) {
            return Result.fail("该博客不存在");
        } else if (judgeResult == 1) {
            return Result.fail("评论已关闭");
        }
        //查询该页面所有评论的数量
        Integer allComment = commentService.countByPageAndIsPublished(page, blogId, null);
        //查询该页面公开评论的数量
        Integer openComment = commentService.countByPageAndIsPublished(page, blogId, true);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<PageComment> pageInfo = new PageInfo<>(commentService.getPageCommentList(page, blogId, (long) -1));
        //PageResult<PageComment> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
        Map<String, Object> map = new HashMap<>();
        map.put("allComment", allComment);
        map.put("closeComment", allComment - openComment);
        map.put("comments", pageInfo);
        return Result.success(map);
    }

    @AccessLimit()
    @PostMapping("comments")
    public Result addComments(@RequestBody CommentDto comment,
                              HttpServletRequest request,
                              @RequestHeader(value = "Authorization",defaultValue = "") String jwt) {
        // 校验评论
        if (StringUtils.isEmpty(comment.getContent()) || comment.getContent().length() > 250 ||
                comment.getPage() == null || comment.getParentCommentId() == null) {
            return Result.fail("评论参数有误");
        }

        //是否访客的评论
        boolean isVisitorComment = false;
        //父评论
        Comment parentComment = null;

        //对于有指定父评论的评论，应该以父评论为准，只判断页面可能会被绕过“评论开启状态检测”
        if (comment.getParentCommentId() != -1) {
            //当前评论为子评论
            parentComment = commentService.getCommentById(comment.getParentCommentId());
            Integer page = parentComment.getPage();
            Long blogId = page == 0 ? parentComment.getBlog().getId() : null;
            comment.setPage(page);
            comment.setBlogId(blogId);
        } else {
            //当前评论为顶级评论
            if (comment.getPage() != 0) {
                comment.setBlogId(null);
            }
        }

        //判断是否可评论
        int judgeResult = commentService.judgeCommentEnabled(comment.getPage(), comment.getBlogId());
        if (judgeResult == 2) {
            return Result.fail("该博客不存在");
        }
        if (judgeResult == 1) {
            return Result.fail("评论已关闭");
        }
        //有Token则为博主评论
        if (JwtUtil.checkTokenIsNotNull(jwt)) {
            // 获取token的subject
            String subject;
            try {
                Claims body = JwtUtil.getTokenBody(jwt);
                subject = body.getSubject();
            } catch (Exception e) {
                e.printStackTrace();
                return Result.fail("Token已失效，请重新登录");
            }

            // 博主评论，根据博主信息设置评论属性
            if (subject.startsWith("admin:")) {
                //Token验证通过，获取Token中用户名
                String username = subject.replace("admin:", "");
                User admin = (User) userService.loadUserByUsername(username);
                if (admin == null) {
                    return Result.fail("博主身份Token已失效，请重新登录！");
                }
                commentService.setAdminComment(comment, request, admin);
                isVisitorComment = false;
            } else {
                //对访客的评论昵称、邮箱合法性校验
                if (StringUtils.isEmpty(comment.getNickname(), comment.getEmail()) || comment.getNickname().length() > 15) {
                    return Result.fail("参数有误");
                }
                commentService.setVisitorComment(comment, request);
                isVisitorComment = true;
            }
            //无token则为访客评论
        } else {
            //对访客的评论昵称、邮箱合法性校验
            if (StringUtils.isEmpty(comment.getNickname(), comment.getEmail()) || comment.getNickname().length() > 15) {
                return Result.fail("参数有误");
            }
            commentService.setVisitorComment(comment, request);
            isVisitorComment = true;
        }
        commentService.saveComment(comment);
        commentService.judgeSendMail(comment, isVisitorComment, parentComment);
        return Result.success();
    }

}
