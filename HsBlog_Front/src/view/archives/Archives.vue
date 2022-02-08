<template>
  <div>
    <div class="ui top attached segment">
      <h2 class="m-text-500">文章归档</h2>
      <p>好! 目前共计 {{ count }} 篇日志。 继续努力。</p>
    </div>
    <div class="ui attached segment">
      <div class="timeline">
        <div :class="colorObj[index%5]" v-for="(value,key,index) in blogMap" :key="index">
          <div class="tl-header">
            <a class="ui large label m-text-500">{{ key }}</a>
          </div>
          <div class="tl-item" v-for="blog in value" :key="blog.id">
            <div class="tl-wrap">
              <span class="tl-date">{{ blog.day }}</span>
              <a href="javascript:;" @click.prevent="toBlog(blog)">
                <div class="ui left pointing label tl-title">{{ blog.title }}</div>
              </a>
            </div>
          </div>
        </div>

        <div class="tl-header">
          <a class="ui black large label m-text-500">Hello World!</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getArchives } from '@/api/archives'

export default {
  name: 'Archives',
  data () {
    return {
      // {"2022年01月":[{"id":1,"title":"test1","day":"10日"},{"id":2,"title":"test1","day":"10日"},{"id":3,"title":"test1","day":"10日"},{"id":4,"title":"test1","day":"10日"},{"id":5,"title":"test1","day":"10日"},{"id":6,"title":"test2","day":"10日"},{"id":7,"title":"外部排序&败者树&多路归并","day":"10日"},{"id":19,"title":"外部排序&败者树&多路归并","day":"10日"},{"id":20,"title":"外部排序&败者树&多路归并","day":"10日"},{"id":21,"title":"外部排序&败者树&多路归并","day":"10日"},{"id":22,"title":"外部排序&败者树&多路归并","day":"10日"},{"id":23,"title":"外部排序&败者树&多路归并","day":"10日"},{"id":24,"title":"图片上传测试","day":"10日"},{"id":25,"title":"图片测试2","day":"10日"}]}
      // '2021年12月': [{'id': 28, 'title': '2021 年度总结', 'day': '31日', 'password': '', 'privacy': false}, {'id': 27, 'title': 'LeetCode 每日一题', 'day': '19日', 'password': '', 'privacy': false}, {'id': 25, 'title': '用 Log4j 花式启动计算器', 'day': '11日', 'password': '', 'privacy': false}], '2021年10月': [{'id': 24, 'title': 'RabbitMQ学习笔记', 'day': '23日', 'password': '', 'privacy': false}], '2021年04月': [{'id': 22, 'title': 'v-if 之 diff 算法踩坑', 'day': '04日', 'password': '', 'privacy': false}], '2021年03月': [{'id': 21, 'title': '短链接服务', 'day': '31日', 'password': '', 'privacy': false}], '2021年02月': [{'id': 20, 'title': 'Redis+定时任务实现访客标识及统计PV、UV', 'day': '05日', 'password': '', 'privacy': false}], '2020年12月': [{'id': 19, 'title': '2020 年度总结', 'day': '31日', 'password': '', 'privacy': false}], '2020年08月': [{'id': 17, 'title': 'Vue 锚点定位导致页面刷新的解决办法', 'day': '23日', 'password': '', 'privacy': false}, {'id': 16, 'title': '又是一次LOL对局中被踢出...', 'day': '06日', 'password': '', 'privacy': false}], '2020年07月': [{'id': 15, 'title': 'Vue电商后台管理系统', 'day': '15日', 'password': '', 'privacy': false}], '2020年06月': [{'id': 14, 'title': 'Python调用腾讯API取屏幕内容翻译并语音输出', 'day': '03日', 'password': '', 'privacy': false}], '2020年05月': [{'id': 13, 'title': '连连看自动玩', 'day': '21日', 'password': '', 'privacy': false}, {'id': 11, 'title': '使用 GitHub + jsDelivr + PicGo + Typora 搭建图床', 'day': '06日', 'password': '', 'privacy': false}, {'id': 12, 'title': '【旧版】博客系统开发总结', 'day': '05日', 'password': '', 'privacy': false}], '2019年12月': [{'id': 10, 'title': '记Java三级项目――五子棋', 'day': '19日', 'password': '', 'privacy': false}], '2019年11月': [{'id': 9, 'title': '使用Ubuntu+Nginx+Flask将Python3应用部署到服务器', 'day': '22日', 'password': '', 'privacy': false}], '2019年10月': [{'id': 8, 'title': 'Python3 基础学习笔记 04——文本词频统计', 'day': '25日', 'password': '', 'privacy': false}, {'id': 7, 'title': 'Python3 基础学习笔记 03——组合数据类型', 'day': '25日', 'password': '', 'privacy': false}, {'id': 6, 'title': 'Python3 基础学习笔记 02——库、异常、循环', 'day': '24日', 'password': '', 'privacy': false}, {'id': 5, 'title': 'Python3 基础学习笔记 01——数据类型及字符串', 'day': '24日', 'password': '', 'privacy': false}, {'id': 4, 'title': 'Sublime Text 3定义自己的Command', 'day': '20日', 'password': '', 'privacy': false}, {'id': 3, 'title': '外部排序&败者树&多路归并', 'day': '17日', 'password': '', 'privacy': false}], '2019年09月': [{'id': 2, 'title': '跳跃列表SkipList', 'day': '15日', 'password': '', 'privacy': false}], '2019年07月': [{'id': 1, 'title': '字符串匹配——KMP算法', 'day': '13日', 'password': '', 'privacy': false}]
      blogMap: {},
      count: 0,
      colorObj: {
        0: 'tl-blue',
        1: 'tl-dark',
        2: 'tl-green',
        3: 'tl-purple',
        4: 'tl-red'
      }
    }
  },
  created () {
    this.getArchives()
  },
  methods: {
    getArchives () {
      getArchives()
        .then(res => {
          if (res.data.code === 200) {
            this.blogMap = res.data.data.blogMap
            this.count = res.data.data.count
          } else {
            this.errorMsg(res.data.message)
          }
        })
        .catch(() => {
          this.errorMsg('请求失败')
        })
    },
    toBlog (blog) {
      this.$store.dispatch('goBlogPage', blog)
    }
  }
}
</script>

<style scoped>
.timeline {
  margin: 20px 0;
}
.tl-header {
  width: 12em;
  text-align: center;
}
.tl-date {
  position: relative;
  top: 10px;
  display: block;
  float: left;
  width: 4.5em;
  margin-left: -7.5em;
  text-align: right;
}
.tl-wrap {
  padding: 15px 0 15px 20px;
  margin-left: 6em;
  border-style: solid;
  border-width: 0 0 0 4px;
}
.tl-wrap:before {
  position: relative;
  top: 15px;
  float: left;
  width: 10px;
  height: 10px;
  margin-left: -27px;
  background: #fff;
  border-color: inherit;
  border-style: solid;
  border-width: 3px;
  border-radius: 50%;
  content: "";
  box-shadow: 0 0 0 4px #fff;
}
.tl-wrap:hover:before {
  background: 0 0;
  border-color: #fff;
}
.tl-item {
text-align: left;
}
.tl-title {
  text-align:left;
  margin-left: 0 !important;
  letter-spacing: 0.3px !important;
  font-size: 14px !important;
  font-weight: 500 !important;
  padding: 12px 15px !important;
}
.tl-blue .tl-header a, .tl-blue .tl-item .tl-title {
  background: #23b7e5 !important;
  color: #fff !important;
}
.tl-blue .tl-item .tl-wrap {
  border-color: #23b7e5;
}
.tl-dark .tl-header a, .tl-dark .tl-item .tl-title {
  background: #3a3f51 !important;
  color: #fff !important;
}
.tl-dark .tl-item .tl-wrap {
  border-color: #3a3f51;
}
.tl-green .tl-header a, .tl-green .tl-item .tl-title {
  background: #27c24c !important;
  color: #fff !important;
}
.tl-green .tl-item .tl-wrap {
  border-color: #27c24c;
}
.tl-purple .tl-header a, .tl-purple .tl-item .tl-title {
  background: #7266ba !important;
  color: #fff !important;
}
.tl-purple .tl-item .tl-wrap {
  border-color: #7266ba;
}
.tl-red .tl-header a, .tl-red .tl-item .tl-title {
  background: #f05050 !important;
  color: #fff !important;
}
.tl-red .tl-item .tl-wrap {
  border-color: #f05050;
}
</style>
