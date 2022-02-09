<template>
  <!--标签云-->
  <div class="ui segments m-box">
    <div class="ui secondary segment"><i class="tags icon"></i>标签云</div>
    <div class="ui yellow segment m-padding-small">
      <router-link :to="`/tag/${tag.tagName}`" class="ui label m-text-500" :class="tag.color" v-for="(tag,index) in tagList" :key="index">
        {{ tag.tagName }}
      </router-link>
    </div>
  </div>
</template>

<script>
import {getAllTags} from '../../api/tag'
export default {
  name: 'TagCloud',
  data () {
    return {
      tagList: []
    }
  },
  mounted () {
    this.setTagList()
  },
  methods: {
    setTagList () {
      getAllTags().then(res => {
        if (res.data.code === 200) {
          this.tagList = res.data.data
        } else {
          this.errorMsg(res.data.message + ': ' + res.data.data)
        }
      })
    }
  }
}
</script>

<style scoped>
.secondary.segment {
  padding: 10px;
}

.m-padding-small {
  padding: 7px;
}

.label {
  margin: 3px !important;
}
</style>
