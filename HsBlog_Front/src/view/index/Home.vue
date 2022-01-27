<template>
  <div>
    <BlogList :getBlogList="getBlogList" :blogList="blogList" :totalPage="totalPage"/>
  </div>
</template>

<script>
import BlogList from '@/view/blog/BlogList'
import {getBlogList} from '@/api/blog'
// import {SET_IS_BLOG_TO_HOME} from '../../store/mutations-types'
// {"data":{"totalPage":5,"list":[{"id":25,"title":"用 Log4j 花式启动计算器","description":"<p>村里刚通网，全世界都在花式启动计算器，脚本小子我也来玩玩</p>\n<p><img src=\"https://cdn.jsdelivr.net/gh/Naccl/blog-resource/blogHosting/2021/12/B01/0afbc74b-11a0-442a-ad96-b1538b18472b.png\" alt=\"0afbc74b-11a0-442a-ad96-b1538b18472b.png\" /></p>\n","createTime":"2021-12-11T12:51:18.000+0000","views":181,"words":346,"readTime":2,"top":true,"password":"","privacy":false,"category":{"id":null,"name":"技术杂烩","blogs":[]},"tags":[{"id":null,"name":"Java","color":"red","blogs":[]}]},{"id":27,"title":"LeetCode 每日一题","description":"<p><img src=\"https://cdn.jsdelivr.net/gh/Naccl/blog-resource/blogHosting/2021/12/B02/LeetCode.png\" alt=\"LeetCode.png\" /></p>\n","createTime":"2021-12-18T17:18:31.000+0000","views":12,"words":434,"readTime":2,"top":false,"password":"","privacy":false,"category":{"id":null,"name":"学习笔记","blogs":[]},"tags":[{"id":null,"name":"算法","color":"black","blogs":[]},{"id":null,"name":"数据结构","color":"black","blogs":[]}]},{"id":24,"title":"RabbitMQ学习笔记","description":"<p>闲来无事小学一手 RabbitMQ</p>\n<p><img src=\"https://cdn.jsdelivr.net/gh/Naccl/blog-resource/blogHosting/2021/10/B01/rabbitmq-logo.png\" alt=\"\" /></p>\n","createTime":"2021-10-23T10:00:02.000+0000","views":1251,"words":17088,"readTime":85,"top":false,"password":"","privacy":false,"category":{"id":null,"name":"学习笔记","blogs":[]},"tags":[{"id":null,"name":"消息队列","color":"blue","blogs":[]},{"id":null,"name":"RabbitMQ","color":"orange","blogs":[]}]},{"id":22,"title":"v-if 之 diff 算法踩坑","description":"<p><img src=\"https://cdn.jsdelivr.net/gh/Naccl/blog-resource/blogHosting/2021/04/B01/first.jpg\" alt=\"\" /></p>\n","createTime":"2021-04-03T16:16:04.000+0000","views":1522,"words":631,"readTime":3,"top":false,"password":"","privacy":false,"category":{"id":null,"name":"技术杂烩","blogs":[]},"tags":[{"id":null,"name":"Vue","color":"green","blogs":[]}]},{"id":21,"title":"短链接服务","description":"<p>最近一直在看 JVM 的教程，准备实习招聘，好久没敲代码，正好前两天看到一个公众号推送，有关短链接服务的应用和实现方式，觉得挺有意思，顺便熟悉一下敲代码的快乐</p>\n<p>长链接生成短链接，访问短链接 302 重定向至原始长链接</p>\n<p>预览地址：<a href=\"https://d.naccl.top/\" target=\"_blank\" rel=\"external nofollow noopener\">https://d.naccl.top/</a></p>\n<p>GitHub：<a href=\"https://github.com/Naccl/ShortURL\" target=\"_blank\" rel=\"external nofollow noopener\">https://github.com/Naccl/ShortURL</a></p>\n<p><img src=\"https://cdn.jsdelivr.net/gh/Naccl/blog-resource/blogHosting/2021/03/B01/001.png\" alt=\"\" /></p>\n","createTime":"2021-03-31T03:12:39.000+0000","views":610,"words":865,"readTime":4,"top":false,"password":"","privacy":false,"category":{"id":null,"name":"个人项目","blogs":[]},"tags":[{"id":null,"name":"Spring Boot","color":"blue","blogs":[]},{"id":null,"name":"Redis","color":"red","blogs":[]},{"id":null,"name":"布隆过滤器","color":"purple","blogs":[]}]}]}}
export default {
  name: 'Home',
  components: {
    BlogList
  },
  data () {
    return {
      blogList: [],
      totalPage: 0,
      getBlogListFinish: false
    }
  },
  mounted () {
    this.getBlogList()
  },
  methods: {
    getBlogList (newPage) {
      getBlogList(newPage).then(res => {
        if (res.data.code === 200) {
          this.blogList = res.data.data.list
          // res.data.totalPage
          this.totalPage = res.data.data.pages
          this.$nextTick(() => {
            // eslint-disable-next-line
            Prism.highlightAll()
          })
          this.getBlogListFinish = true
        } else {
          this.errorMsg(res.data.message)
        }
      }).catch(() => {
        this.errorMsg('请求失败')
      })
    }
  }
}
</script>

<style scoped>
h1 {
  color: #141fb9;
}
</style>
