package com.hs.service.impl;

import com.hs.entity.Friend;
import com.hs.entity.User;
import com.hs.constants.RedisKey;
import com.hs.entity.Comment;
import com.hs.model.dto.CommentDto;
import com.hs.model.vo.PageComment;
import com.hs.repository.CommentMapper;
import com.hs.util.EncodeUtil;
import com.hs.util.IpAddressUtils;
import com.hs.util.StringUtils;
import com.hs.util.image.QQInfoUtils;
import com.hs.service.*;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Hs
 * @Date 2022/2/10 19:45
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    BlogService blogService;
    @Autowired
    AboutService aboutService;
    @Autowired
    FriendService friendService;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    RedisService redisService;

    private String blogName;
    private String cmsUrl;
    private String websiteUrl;

    @Value("${custom.blog.name}")
    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    @Value("${custom.url.cms}")
    public void setCmsUrl(String cmsUrl) {
        this.cmsUrl = cmsUrl;
    }

    @Value("${custom.url.website}")
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    @Override
    public List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId) {
        List<Comment> comments = commentMapper.getListByPageAndParentCommentId(page, blogId, parentCommentId);
        for (Comment c : comments) {
            //递归查询子评论及其子评论
            List<Comment> replyComments = getListByPageAndParentCommentId(page, blogId, c.getId());
            c.setReplyComments(replyComments);
        }
        return comments;
    }

    @Override
    public List<PageComment> getPageCommentList(Integer page, Long blogId, Long parentCommentId) {
        List<PageComment> comments = getPageCommentListByPageAndParentCommentId(page, blogId, parentCommentId);
        for (PageComment c : comments) {
            List<PageComment> tmpComments = new ArrayList<>();
            getReplyComments(tmpComments, c.getReplyComments());
            //对于两列评论来说，按时间顺序排列应该比树形更合理些
            //排序一下
            Comparator<PageComment> comparator = (c1, c2) -> c1.getCreateTime().compareTo(c2.getCreateTime());
            tmpComments.sort(comparator);

            c.setReplyComments(tmpComments);
        }
        return comments;
    }

    @Override
    public Comment getCommentById(Long id) {
        Comment comment = commentMapper.getCommentById(id);
        if (comment == null) {
            throw new PersistenceException("评论不存在");
        }
        return comment;
    }

    /**
     * 将所有子评论递归取出到一个List中
     *
     * @param comments
     * @return
     */
    private void getReplyComments(List<PageComment> tmpComments, List<PageComment> comments) {
        for (PageComment c : comments) {
            tmpComments.add(c);
            getReplyComments(tmpComments, c.getReplyComments());
        }
    }

    private List<PageComment> getPageCommentListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId) {
        List<PageComment> comments = commentMapper.getPageCommentListByPageAndParentCommentId(page, blogId, parentCommentId);
        for (PageComment c : comments) {
            List<PageComment> replyComments = getPageCommentListByPageAndParentCommentId(page, blogId, c.getId());
            c.setReplyComments(replyComments);
        }
        return comments;
    }

    @Transactional
    @Override
    public void updateCommentPublishedById(Long commentId, Boolean published) {
        if (commentMapper.updateCommentPublishedById(commentId, published) != 1) {
            throw new PersistenceException("操作失败");
        }
    }

    @Transactional
    @Override
    public void updateCommentNoticeById(Long commentId, Boolean notice) {
        if (commentMapper.updateCommentNoticeById(commentId, notice) != 1) {
            throw new PersistenceException("操作失败");
        }
    }

    @Transactional
    @Override
    public void deleteCommentById(Long commentId) {
        List<Comment> comments = getAllReplyComments(commentId);
        for (Comment c : comments) {
            delete(c);
        }
        if (commentMapper.deleteCommentById(commentId) != 1) {
            throw new PersistenceException("评论删除失败");
        }
    }

    @Transactional
    @Override
    public void deleteCommentsByBlogId(Long blogId) {
        commentMapper.deleteCommentsByBlogId(blogId);
    }

    @Transactional
    @Override
    public void updateComment(Comment comment) {
        if (commentMapper.updateComment(comment) != 1) {
            throw new PersistenceException("评论修改失败");
        }
    }

    @Override
    public int countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished) {
        return commentMapper.countByPageAndIsPublished(page, blogId, isPublished);
    }

    @Transactional
    @Override
    public void saveComment(CommentDto comment) {
        if (commentMapper.saveComment(comment) != 1) {
            throw new PersistenceException("评论失败");
        }
    }

    /**
     * 查询对应页面评论是否开启
     *
     * @param page   页面分类（0普通文章，1关于我，2友链）
     * @param blogId 如果page==0，需要博客id参数，校验文章是否公开状态
     * @return 0:公开可查询状态 1:评论关闭 2:该博客不存在
     */
    @Override
    public int judgeCommentEnabled(Integer page, Long blogId) {
        switch (page){
            case 0:
                //普通博客
                Boolean commentEnabled = blogService.getCommentEnabledByBlogId(blogId);
                Boolean published = blogService.getPublishedByBlogId(blogId);
                if (commentEnabled == null || published == null) {//未查询到此博客
                    return 2;
                } else if (!published) {//博客未公开
                    return 2;
                } else if (!commentEnabled) {//博客评论已关闭
                    return 1;
                }
            case 1:
                if (!aboutService.getAboutCommentEnabled()) {//页面评论已关闭
                    return 1;
                }
            case 2:
                Friend friend = friendService.getFriendInfo();
                if (!friend.getCommentEnable()) {
                    return 1;
                }
        }
        return 0;
    }

    /**
     * 递归删除子评论
     *
     * @param comment 需要删除子评论的父评论
     * @return
     */
    private void delete(Comment comment) {
        for (Comment c : comment.getReplyComments()) {
            delete(c);
        }
        if (commentMapper.deleteCommentById(comment.getId()) != 1) {
            throw new PersistenceException("评论删除失败");
        }
    }

    /**
     * 按id递归查询子评论
     *
     * @param parentCommentId 需要查询子评论的父评论id
     * @return
     */
    @Override
    public List<Comment> getAllReplyComments(Long parentCommentId) {
        List<Comment> comments = commentMapper.getListByParentCommentId(parentCommentId);
        for (Comment c : comments) {
            List<Comment> replyComments = getAllReplyComments(c.getId());
            c.setReplyComments(replyComments);
        }
        return comments;
    }

    /**
     * 设置博主评论属性
     *
     * @param comment 评论DTO
     * @param request 获取ip
     * @param admin   博主信息
     */
    @Override
    public void setAdminComment(CommentDto comment, HttpServletRequest request, User admin) {
        comment.setAdminComment(true);
        comment.setCreateTime(new Date());
        comment.setPublished(true);
        comment.setAvatar(admin.getAvatarImgUrl());
        comment.setWebsite("/");
        comment.setNickname(admin.getUsername());
        comment.setEmail(admin.getEmail());
        comment.setIp(IpAddressUtils.getIpAddress(request));
        comment.setNotice(false);
    }

    /**
     * 设置访客评论属性
     *
     * @param comment 评论DTO
     * @param request 用于获取ip
     */
    @Override
    public void setVisitorComment(CommentDto comment, HttpServletRequest request) {
        String commentNickname = comment.getNickname();
        try {
            if (QQInfoUtils.isQQNumber(commentNickname)) {
                comment.setQq(commentNickname);
                comment.setNickname(QQInfoUtils.getQQNickname(commentNickname));
                // 查询数据库，判断该QQ号的头像是否已上传
                String urlByQQ = (String) redisService.getValueByHashKey(RedisKey.QQ_AVATAR_GITHUB_URL, commentNickname);
                if (StringUtils.isEmpty(urlByQQ)){
                    String avatar = QQInfoUtils.getQQAvatarURLByGithubUpload(commentNickname);
                    comment.setAvatar(avatar);
                    redisService.saveKVToHash(RedisKey.QQ_AVATAR_GITHUB_URL,commentNickname,avatar);
                } else {
                  comment.setAvatar(urlByQQ);
                }
            } else {
                comment.setNickname(comment.getNickname().trim());
                setCommentRandomAvatar(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
            comment.setNickname(comment.getNickname().trim());
            setCommentRandomAvatar(comment);
        }

        //set website
        String website = comment.getWebsite().trim();
        if (!"".equals(website) && !website.startsWith("http://") && !website.startsWith("https://")) {
            website = "http://" + website;
        }
        comment.setAdminComment(false);
        comment.setCreateTime(new Date());
        comment.setPublished(true);//默认不需要审核
        comment.setWebsite(website);
        comment.setEmail(comment.getEmail().trim());
        comment.setIp(IpAddressUtils.getIpAddress(request));
    }

    /**
     * 对于昵称不是QQ号的评论，根据昵称Hash设置头像
     *
     * @param comment 评论DTO
     */
    @Override
    public void setCommentRandomAvatar(CommentDto comment) {
        //设置随机头像
        long nicknameHash = EncodeUtil.getMurmurHash32(comment.getNickname());//根据评论昵称取Hash，保证每一个昵称对应一个头像
        long num = nicknameHash % 6 + 1;//计算对应的头像
        String avatar = "/img/comment-avatar/" + num + ".jpg";
        comment.setAvatar(avatar);
    }

    /**
     * 判断是否发送邮件
     * 6种情况：
     * 1.我以父评论提交：不用邮件提醒
     * 2.我回复我自己：不用邮件提醒
     * 3.我回复访客的评论：只提醒该访客
     * 4.访客以父评论提交：只提醒我自己
     * 5.访客回复我的评论：只提醒我自己
     * 6.访客回复访客的评论(即使是他自己先前的评论)：提醒我自己和他回复的评论
     *
     * @param comment          当前评论
     * @param isVisitorComment 是否访客评论
     * @param parentComment    父评论
     */
    @Override
    public void judgeSendMail(CommentDto comment, boolean isVisitorComment, Comment parentComment) {
        if (parentComment != null && !parentComment.getAdminComment() && parentComment.getNotice()) {
            //我回复访客的评论，且对方接收提醒，邮件提醒对方(3)
            //访客回复访客的评论(即使是他自己先前的评论)，且对方接收提醒，邮件提醒对方(6)
            sendMailToParentComment(parentComment, comment);
        }
        if (isVisitorComment) {
            //访客以父评论提交，只邮件提醒我自己(4)
            //访客回复我的评论，邮件提醒我自己(5)
            //访客回复访客的评论，不管对方是否接收提醒，都要提醒我有新评论(6)
            sendMailToMe(comment);
        }
    }

    /**
     * 发送邮件提醒回复对象
     *
     * @param parentComment 父评论
     * @param comment       当前评论
     */
    @Override
    public void sendMailToParentComment(Comment parentComment, CommentDto comment) {
        String path = "";
        String title = "";
        switch (comment.getPage()){
            case 0:
                //普通博客
                title = parentComment.getBlog().getTitle();
                path = "/blog/" + comment.getBlogId();
                break;
            case 1:
                //关于我页面
                title = "关于我";
                path = "/about";
                break;
            case 2:
                //友链页面
                title = "友人帐";
                path = "/friends";
                break;
            default:
                break;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("parentNickname", parentComment.getNickname());
        map.put("nickname", comment.getNickname());
        map.put("title", title);
        map.put("time", comment.getCreateTime());
        map.put("parentContent", parentComment.getContent());
        map.put("content", comment.getContent());
        map.put("url", websiteUrl + path);
        String toAccount = parentComment.getEmail();
        String subject = "您在 " + blogName + " 的评论有了新回复";
        // TODO: 2022/2/10
        //mailUtils.sendHtmlTemplateMail(map, toAccount, subject, "reminderOfMorning.html");
    }

    /**
     * 发送邮件提醒我自己
     *
     * @param comment 当前评论
     */
    @Override
    public void sendMailToMe(CommentDto comment) {
        String path = "";
        String title = "";
        switch (comment.getPage()){
            case 0:
                //普通博客
                title = blogService.getBlogTitleById(comment.getBlogId());
                path = "/blog/" + comment.getBlogId();
                break;
            case 1:
                //关于我页面
                title = "关于我";
                path = "/about";
                break;
            case 2:
                //友链页面
                title = "友人帐";
                path = "/friends";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("time", comment.getCreateTime());
        map.put("nickname", comment.getNickname());
        map.put("content", comment.getContent());
        map.put("ip", comment.getIp());
        map.put("email", comment.getEmail());
        map.put("status", comment.getPublished() ? "公开" : "待审核");
        map.put("url", websiteUrl + path);
        map.put("manageUrl", cmsUrl + "/comments");
        // TODO: 2022/2/10  
        //String toAccount = mailProperties.getUsername();
        String subject = blogName + " 收到新评论";
        //mailUtils.sendHtmlTemplateMail(map, toAccount, subject, "owner.html");
    }
}
