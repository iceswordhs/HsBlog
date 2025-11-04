# HsBlog
前后端分离的博客


待完成工作：
- [x] 评论页数问题
- [x] 喜欢语录功能
- [x] 管理端visitor includes报错问题
- [x] 博客编写页面美化
- [x] markdown格式前端显示问题的解决
- [x] 博客保存问题
- [x] 访客标识问题(相同IP被视为了不同访客)
- [x] 管理页面图标美化
- [x] 新增标签颜色、无用标签清理
- [x] 文章管理推荐选项
- [ ] 每天发邮件提醒自己定时任务完成结果，并附带昨天列出的工作事项以及喜欢的警示语录

     

# 网站地址：

前端：https://icesword.site
首页：
![image](https://user-images.githubusercontent.com/75706056/172975625-f5c39600-6376-44b5-9ad8-fa84bb75a21e.png)
动态：
![image](https://user-images.githubusercontent.com/75706056/172975703-6e83aad7-4620-4a47-9f8e-85567750101e.png)


后台：https://admin.icesword.site
![image](https://user-images.githubusercontent.com/75706056/172975757-1cdca3e6-1c0d-40a9-a8e3-5c47d76c10ac.png)
![image](https://user-images.githubusercontent.com/75706056/172975881-ecb75bc2-95e3-445d-b2d2-ca8dded1b344.png)

# 下载env.example文件
![image.png](https://fastly.jsdelivr.net/gh/iceswordhs/HsResource/Blog/image/2025/11/20251104075720671image.png)
并在本地将其改名为.env文件
同时可以自己填写相关配置(不填也能启动，只是部分功能无法使用)

# 下载docker.compose文件
如果你电脑上还没下载docker，请先安装docker，同时设置镜像仓库
可参考网络教程
或者https://icesword.site/blog/8

此时如果你电脑上已经安装了docker，并且下载了docker-compose.yaml文件

在docker-compose.yaml文件所在目录下启动终端，使用`docker compose up -d`启动即可，注意：.env文件应该和docker-compose.yaml文件在同一目录下

此时访问localhost应该就可以看到前台展示页面
访问localhost/admin，并输入
账户:Hs 
密码:hspassword
即可登陆后台管理页面


# 在服务器上部署该项目并绑定域名
要求必须有自己的域名以及一个子域名
域名用于前台展示，子域名用于后台管理页面
将docker-compose-ssl.yaml文件和.env_example文件下载到服务器同一文件夹下，创建nginx文件夹，在此文件夹下放入下载的nginx-ssl.conf文件，同时修改nginx-ssl.conf文件中的域名，改为你自己的域名。

在docker-compose-ssl.yaml文件夹下使用命令`docker compose -f docker-compose-ssl.yam up -d`启动项目

如果需要使用https，还需要在nginx目录下文件夹下创建一个ssl文件夹，同时执行以下命令
```language
# 你的域名所在dns的token 这里是腾讯云dnspod的，可自行搜索相关dns的acme代码

export DP_Id=""
export DP_Key=""

# 申请证书（根据你的验证方式选择，这里以DNS验证为例）
acme.sh --issue --dns dns_dp -d admin.icesword.site
# 申请 icesword 的证书，如需泛域名可加上 -d *.icesword
acme.sh --issue --dns dns_dp -d icesword.site -d www.icesword.site

# 安装证书到指定目录
# 为 adminicesword 安装证书
mkdir -p ./nginx/ssl/live/admin.icesword.site
acme.sh --install-cert -d admin.icesword.site     --key-file ./nginx/ssl/live/admin.icesword.site/privkey.pem     --fullchain-file ./nginx/ssl/live/admin.icesword.site/fullchain.pem     --reloadcmd "docker compose -f /root/hs_blog/docker-compose-ssl.yaml restart nginx"  # 替换为你的Nginx服务名

# 为 icesword 安装证书
mkdir -p ./nginx/ssl/live/icesword.site
acme.sh --install-cert -d icesword.site \
    --key-file ./nginx/ssl/live/icesword.site/privkey.pem \
    --fullchain-file ./nginx/ssl/live/icesword.site/fullchain.pem \
    --reloadcmd "docker compose -f /root/hs_blog/docker-compose-ssl.yaml restart nginx" 
```


