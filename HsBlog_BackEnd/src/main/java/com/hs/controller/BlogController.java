package com.hs.controller;

import com.github.pagehelper.PageInfo;
import com.hs.annotation.VisitLogger;
import com.hs.constants.CodeType;
import com.hs.entity.Blog;
import com.hs.model.vo.BlogListItem;
import com.hs.model.vo.SearchBlog;
import com.hs.service.BlogService;
import com.hs.service.impl.UserServiceImpl;
import com.hs.util.JwtUtil;
import com.hs.util.Result;
import com.hs.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/3 20:08
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/getAllBlogs")
    public Result<PageInfo<BlogListItem>> getAllBlog(){
        return Result.success(blogService.getAllBlog());
    }

    @VisitLogger(behavior = "搜索博客")
    @GetMapping("/searchBlog")
    public Result getSearchBlogListIsPublished(@RequestParam String query){
        //校验关键字字符串合法性
        if (StringUtils.isEmpty(query) || StringUtils.hasSpecialChar(query) || query.trim().length() > 20) {
            return Result.fail(CodeType.PARAMS_ERROR);
        }
        List<SearchBlog> searchBlogs = blogService.getSearchBlogListIsPublished(query.trim());
        return Result.success(searchBlogs);
    }

    @VisitLogger(behavior = "访问页面", content = "首页")
    @RequestMapping("/getPageBlog")
    public Result<PageInfo<BlogListItem>> getPageBlogIsPublished(@RequestParam(defaultValue = "1") int pageNum){
        PageInfo<BlogListItem> allBlog = blogService.getPageBlogIsPublished(pageNum);
        return Result.success(allBlog);
    }

    @VisitLogger(behavior = "查看博客")
    @RequestMapping("/getBlogById")
    public Result getBlogById(@RequestParam Long id,
                                    @RequestHeader(value = "Authorization",defaultValue = "") String jwt){
        Boolean published = blogService.getPublishedByBlogId(id);
        if (!published&& JwtUtil.checkTokenIsNotNull(jwt)) {
            try {
                String subject = JwtUtil.getTokenBody(jwt).getSubject();
                if (subject.startsWith("admin:")) {
                    subject.replace("admin:","");
                    UserDetails user = userService.loadUserByUsername(subject);
                    if (user==null){
                        return Result.fail("用户不存在,请登录！");
                    }
                }
            }catch (Exception e){
                return Result.fail("token已过期，请重新登陆");
            }

        }
        Blog blogById = blogService.getBlogById(id);
        return Result.success(blogById);
    }
}
