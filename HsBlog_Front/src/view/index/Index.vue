<template>
  <div>
    <Nav></Nav>
    <div class="main">
      <div class="m-padded-tb-big">
        <div class="ui container">
          <div class="ui stackable grid">
            <!--左侧-->
            <div class="three wide column m-mobile-hide decorator">
              <Introduction :class="{'m-display-none':focusMode}"/>
            </div>
            <!--中间-->
            <div class="ten wide column site">
              <keep-alive include="Home">
                <router-view/>
              </keep-alive>
            </div>
            <!--右侧-->
            <div class="three wide column m-mobile-hide decorator">
              <TimeLife :class="{'m-display-none':focusMode}"/>
              <TagCloud :tagList="tagList" :class="{'m-display-none':focusMode}"/>
              <RandomBlog :randomBlogList="randomBlogList" :class="{'m-display-none':focusMode}"/>
              <!--只在文章页面显示目录-->
              <Tocbot v-if="$route.name==='Blog'"/>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!--APlayer-->
    <div class="m-mobile-hide">
      <MyAPlayer/>
    </div>
    <!--回到顶部-->
    <el-backtop style="box-shadow: none;background: none;">
      <img src="/static/paper-plane.png" style="width: 40px;height: 40px;">
    </el-backtop>
    <!--底部footer-->
    <Footer :siteInfo="siteInfo" :badges="badges" :newBlogList="newBlogList" :sentence="sentence"/>
  </div>
</template>
<script>
import Nav from '@/components/Nav'
import Footer from '@/view/index/Footer'
import BlogItem from '@/view/blog/BlogItem'
import Tocbot from '@/view/blog/Tocbot'
import MyAPlayer from '@/view/index/MyAPlayer'
import TagCloud from '@/view/tag/TagCloud'
import TimeLife from '@/view/index/TimeLife'
import Introduction from '@/view/index/Introduction'
import RandomBlog from '@/view/index/RandomBlog'

import {getSite, getSentence} from '@/api/index'
import {mapState} from 'vuex'
import {SAVE_CLIENT_SIZE, SAVE_SITE_INFO, SAVE_INTRODUCTION, RESTORE_COMMENT_FORM} from '@/store/mutations-types'

export default {
  name: 'Index',
  components: {TagCloud, RandomBlog, Nav, Footer, BlogItem, Tocbot, MyAPlayer, TimeLife, Introduction},
  data () {
    return {
      siteInfo: {
        blogName: ''
      },
      randomBlogList: [],
      sentence: {
        value: ' ',
        nameZh: ''
      },
      categoryList: [],
      tagList: [],
      newBlogList: [],
      badges: []
    }
  },
  created () {
    this.getSite()
    // 从localStorage恢复之前的评论信息
    this.$store.commit(RESTORE_COMMENT_FORM)
  },
  computed: {
    ...mapState(['focusMode'])
  },
  mounted () {
    // 保存可视窗口大小
    this.$store.commit(SAVE_CLIENT_SIZE, {clientHeight: document.body.clientHeight, clientWidth: document.body.clientWidth})
    window.onresize = () => {
      this.$store.commit(SAVE_CLIENT_SIZE, {clientHeight: document.body.clientHeight, clientWidth: document.body.clientWidth})
    }
  },
  methods: {
    getSite () {
      getSite().then(res => {
        if (res.data.code === 200) {
          this.siteInfo = res.data.data.siteSetting
          this.siteInfo.copyright = JSON.parse(this.siteInfo.copyright)
          this.badges = res.data.data.badges
          this.newBlogList = res.data.data.newBlogList
          this.randomBlogList = res.data.data.randomBlogList
          this.tagList = res.data.data.tagList
          this.$store.commit(SAVE_SITE_INFO, this.siteInfo)
          this.$store.commit(SAVE_INTRODUCTION, res.data.data.introduction)
          document.title = this.$route.meta.title + this.siteInfo.webTitleSuffix
        }
      })
      getSentence().then(res => {
        if (res.data.code === 200) {
          this.sentence = res.data.data
        }
      })
    }
  }
}

</script>
<style>
.site {
  /*display: flex;*/
  min-height: 70vh; /* 没有元素时，也把页面撑开至100% */
  flex-direction: column;
}
.main {
  margin-top: 40px;
  flex: 1;
}
.main .ui.container {
  width: 1400px!important;
  margin-left: auto !important;
  margin-right: auto !important;
  text-align: left;
}
.ui.grid .three.column {
  padding: 0;
}
.ui.grid .ten.column {
  padding-top: 0;
}
.m-display-none {
  display: none !important;
}
</style>
