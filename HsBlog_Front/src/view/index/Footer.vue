<template>
  <sui-segment inverted vertical class="footer">
    <sui-container text-align="center">
      <sui-grid class="inverted" stackable divided>
        <sui-grid-row>
          <sui-grid-column :width="3" v-for="n in 3" :key="n">
            <sui-list class="inverted" link>
              <h4 is="sui-header" :content="`Group ${n}`" class="inverted" />
              <a is="sui-list-item" href="#">Link One</a>
              <a is="sui-list-item" href="#">Link Two</a>
              <a is="sui-list-item" href="#">Link Three</a>
              <a is="sui-list-item" href="#">Link Four</a>
            </sui-list>
          </sui-grid-column>
          <sui-grid-column :width="3">
            <h4 is="sui-header" content="Footer Header" class="inverted" />
            <p>
              {{runTime}}
            </p>
          </sui-grid-column>
        </sui-grid-row>
      </sui-grid>

      <sui-divider inverted section />
      <p>
        <span style="margin-right: 10px">Copyright © 2019 - 2022</span>
        <router-link to="/" style="color:#ffe500">HS'BLOG</router-link>
        <span style="margin: 0 15px">|</span>
        <img src="/assets/img/beian.png" alt="" class="beian">
        <a rel="external nofollow noopener" href="http://www.beian.miit.gov.cn/" target="_blank" style="color:#ffe500">闽ICP备20005222号</a>
      </p>
<!--      <p class="m-text-thin m-text-spaced m-opacity-tiny">-->
<!--        <span style="margin-right: 10px" v-if="siteInfo.copyright">{{ siteInfo.copyright.title }}</span>-->
<!--        <router-link to="/" style="color:#ffe500" v-if="siteInfo.copyright">{{ siteInfo.copyright.siteName }}</router-link>-->
<!--        <span style="margin: 0 15px" v-if="siteInfo.copyright && siteInfo.beian">|</span>-->
<!--        <img src="/assets/img/beian.png" alt="" class="beian" v-if="siteInfo.beian">-->
<!--        <a rel="external nofollow noopener" href="http://www.beian.miit.gov.cn/" target="_blank" style="color:#ffe500">{{ siteInfo.beian }}</a>-->
<!--      </p>-->

<!--      <sui-image src="/static/images/logo.png" size="mini" centered />-->
<!--      <sui-list horizontal divided link class="inverted">-->
<!--        <a is="sui-list-item" href="#">Site Map</a>-->
<!--        <a is="sui-list-item" href="#">Contact Us</a>-->
<!--        <a is="sui-list-item" href="#">Terms and Conditions</a>-->
<!--        <a is="sui-list-item" href="#">Privacy Policy</a>-->
<!--      </sui-list>-->
    </sui-container>
  </sui-segment>
</template>

<script>
import {runtime} from '../../util/siteRunTime'

export default {
  name: 'Footer',
  data () {
    return {
      runTime: ''
    }
  },
  methods: {
    setRunTime () {
      this.runTime = runtime()
    }
  },
  mounted () {
    this.runTime = setInterval(this.setRunTime, 1000)
  }
}
// export default {
//   name: 'Footer',
//   props: {
//     siteInfo: {
//       type: Object,
//       required: true
//     },
//     badges: {
//       type: Array,
//       required: true
//     },
//     newBlogList: {
//       type: Array,
//       required: true
//     },
//     hitokoto: {
//       type: Object,
//       required: true
//     }
//   },
//   methods: {
//     toBlog (blog) {
//       this.$store.dispatch('goBlogPage', blog)
//     }
//   }
// }
</script>

<style scoped>
@import '/badge.css';

.github-badge a {
  color: #fff;
}

.beian {
  width: 17px;
  height: 17px;
  margin-bottom: -4px;
  margin-right: 5px;
}

.footer {
  margin: 5em 0em 0em;
  padding: 5em 0em;
}
</style>
