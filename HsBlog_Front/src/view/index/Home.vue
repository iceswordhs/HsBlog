<template>
  <div>
    <BlogList :getBlogList="getBlogList" :blogList="blogList" :totalPage="totalPage"/>
  </div>
</template>

<script>
import BlogList from '@/view/blog/BlogList'
import {getBlogList} from '@/api/blog'
// import {SET_IS_BLOG_TO_HOME} from '../../store/mutations-types'
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
          this.totalPage = res.data.data.pages
          this.$nextTick(() => {
            // eslint-disable-next-line
            Prism.highlightAll()
          })
          this.getBlogListFinish = true
        } else {
          this.errorMsg(res.data.message + ': ' + res.data.data)
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
