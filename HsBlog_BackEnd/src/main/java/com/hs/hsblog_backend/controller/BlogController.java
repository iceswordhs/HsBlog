package com.hs.hsblog_backend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.hsblog_backend.annotation.VisitLogger;
import com.hs.hsblog_backend.constants.CodeType;
import com.hs.hsblog_backend.entity.Blog;
import com.hs.hsblog_backend.model.vo.BlogListItem;
import com.hs.hsblog_backend.model.vo.SearchBlog;
import com.hs.hsblog_backend.service.BlogService;
import com.hs.hsblog_backend.util.Result;
import com.hs.hsblog_backend.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/12/3 20:08
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

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
    public Result<Blog> getBlogById(@RequestParam Long id){
        Blog blogById = blogService.getBlogById(id);
        return Result.success(blogById);
    }
}
