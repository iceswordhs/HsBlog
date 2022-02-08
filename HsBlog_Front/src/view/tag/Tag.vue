<template>
  <div>
    <div class="ui top segment" style="text-align: center">
      <h2 class="m-text-500">标签 {{ tagName }} 下的文章</h2>
    </div>
    <BlogList :getBlogList="getBlogList" :blogList="blogList" :totalPage="totalPage"/>
  </div>
</template>

<script>
import BlogList from '@/view/blog/BlogList'
import {getBlogByTag} from '@/api/tag'

export default {
  name: 'Tag',
  components: {BlogList},
  data () {
    return {
      blogList: [],
      totalPage: 0
    }
  },
  watch: {
    // 在当前组件被重用时，要重新获取博客列表
    '$route.fullPath' () {
      if (this.$route.name === 'Tag') {
        this.getBlogList()
      }
    }
  },
  created () {
    this.getBlogList()
  },
  computed: {
    tagName () {
      return this.$route.params.name
    }
  },
  methods: {
    getBlogList (pageNum) {
      getBlogByTag(this.tagName, pageNum).then(res => {
        if (res.data.code === 200) {
          this.blogList = res.data.data.list
          this.totalPage = res.data.data.pages
          this.$nextTick(() => {
            // eslint-disable-next-line
							Prism.highlightAll()
          })
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

</style>
