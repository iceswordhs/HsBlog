package com.hs.hsblog_backend.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.annotation.OperationAnnotation;
import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.entity.Category;
import com.hs.hsblog_backend.entity.NullObject;
import com.hs.hsblog_backend.service.BlogService;
import com.hs.hsblog_backend.service.CategoryService;
import com.hs.hsblog_backend.service.CommentService;
import com.hs.hsblog_backend.util.JwtUtil;
import com.hs.hsblog_backend.util.Result;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hs
 * @Date 2021/12/11 17:32
 */
@RestController
@RequestMapping("/admin")
public class BlogAdminController {
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CommentService commentService;

    /**
     * 保存博客  前端传入 文章标题、分类、标签、描述、公开或私密、正文、文章展示图
     * 返回标题
     * @author huangshuai
     * @Date 2021/12/14 14:09
     * @param blog
     * @return com.hs.hsblog_backend.util.Result<String>
     */
    @OperationAnnotation("/保存博客")
    @PostMapping("/saveBlog")
    public Result<String> saveBlog(@RequestBody Blog blog){
        String blogTitle = blogService.saveBlog(blog);
        return Result.success(blogTitle);
    }

    @GetMapping("/blog")
    public Result<Blog> getBlogById(@RequestParam Long id,
                                    @RequestHeader(value = "Authorization",defaultValue = "") String jwt){
        Boolean admin=false;
        Claims tokenBody = JwtUtil.getTokenBody(jwt);
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) tokenBody.get("authorities"));
        for (GrantedAuthority a : authorities) {
            if (a.getAuthority().equals("ROLE_admin")) {
                admin=true;
            }
        }
        Blog blogById = blogService.getBlogEditById(id,admin);
        return Result.success(blogById);
    }

    @OperationAnnotation("/按Id删除博客")
    @PostMapping("/deleteBlogById")
    public Result<NullObject> deleteBlogById(@RequestParam Long id){
        blogService.deleteBlogById(id);
        // 清除评论
        commentService.deleteCommentsByBlogId(id);
        return Result.success();
    }

    @GetMapping("/manageBlog")
    public Result<Map<String,Object>> manageBlog(@RequestParam(defaultValue = "") String title,
                                                 @RequestParam(defaultValue = "") Long categoryId,
                                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                                 @RequestParam(defaultValue = "10") Integer pageSize){
        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogService.getBlogListByTitleAndCategoryId(title, categoryId));
        List<Category> categories = categoryService.getAllCategories();
        Map<String, Object> map = new HashMap<>();
        map.put("blogs", pageInfo);
        map.put("categories", categories);
        return Result.success(map);
    }

    @OperationAnnotation("更新博客置顶状态")
    @PostMapping("/blog/top")
    public Result<NullObject> updateBlogTop(@RequestParam Long id, @RequestParam Boolean top){
        blogService.updateBlogTopById(id, top);
        return Result.success();
    }

    @OperationAnnotation("更新博客公开状态")
    @PostMapping("/blog/published")
    public Result<NullObject> updateBlogPublished(@RequestParam Long id, @RequestParam Boolean published){
        blogService.updateBlogPublishedById(id, published);
        return Result.success();
    }

    @OperationAnnotation("更新博客推荐状态")
    @PostMapping("/blog/recommend")
    public Result<NullObject> updateBlogRecommend(@RequestParam Long id, @RequestParam Boolean published){
        blogService.updateBlogRecommendById(id, published);
        return Result.success();
    }
}
