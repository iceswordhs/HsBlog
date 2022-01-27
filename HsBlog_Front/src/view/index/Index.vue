<template>
  <div>
    <Nav></Nav>
    <div class="main">
      <div class="m-padded-tb-big">
        <div class="ui container">
          <div class="ui stackable grid">
            <!--左侧-->
            <div class="three wide column m-mobile-hide">
              <Introduction :class="{'m-display-none':focusMode}"/>
            </div>
            <!--中间-->
            <div class="nine wide column site">
              <keep-alive include="Home">
                <router-view/>
              </keep-alive>
            </div>
            <!--右侧-->
            <div class="three wide column m-mobile-hide">
<!--              <RandomBlog :randomBlogList="randomBlogList" :class="{'m-display-none':focusMode}"/>-->
              <TagCloud :class="{'m-display-none':focusMode}"/>
              <TimeLife :class="{'m-display-none':focusMode}"/>
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
      <img src="/assets/img/paper-plane.png" style="width: 40px;height: 40px;">
    </el-backtop>
    <!--底部footer-->
    <Footer></Footer>
<!--    <Footer :siteInfo="siteInfo" :badges="badges" :newBlogList="newBlogList" :hitokoto="hitokoto"/>-->
  </div>
</template>
<script>
import myCard from '@/view/index/MyCard'
import Nav from '@/components/Nav'
import Footer from '@/view/index/Footer'
import BlogItem from '@/view/blog/BlogItem'
import Tocbot from '@/view/blog/Tocbot'
import MyAPlayer from '@/view/index/MyAPlayer'
import TagCloud from '@/view/tag/TagCloud'
import TimeLife from '@/view/index/TimeLife'
import Introduction from '@/view/index/Introduction'

import {getSite} from '@/api/index'
import {mapState} from 'vuex'
import {SAVE_CLIENT_SIZE, SAVE_INTRODUCTION, SAVE_SITE_INFO, RESTORE_COMMENT_FORM} from '@/store/mutations-types'

export default {
  name: 'Index',
  components: {TagCloud, Nav, myCard, Footer, BlogItem, Tocbot, MyAPlayer, TimeLife, Introduction},
  data () {
    return {

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
          this.siteInfo = res.data.data.siteInfo
          this.badges = res.data.data.badges
          this.$store.commit(SAVE_SITE_INFO, this.siteInfo)
          this.$store.commit(SAVE_INTRODUCTION, res.data.data.introduction)
          // document.title = this.$route.meta.title + this.siteInfo.webTitleSuffix
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
  width: 1400px !important;
  margin-left: auto !important;
  margin-right: auto !important;
  text-align: center;
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
