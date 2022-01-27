<template>
  <div>
    <mavon-editor v-model="content"
                  fontSize="16px"
                  :style="{'z-index':editorZIndex}"
                  @imgAdd="uploadImg"
                  @fullScreen="fullScreen"
                  ref="md">
    </mavon-editor>
    <p>字符为{{wordcount}}</p>
  </div>
</template>

<script>
import {uploadImage} from "@/api/uploadImage";
import {countCharacters} from "@homegrown/word-counter";

export default {
  name: "MarkdownEditor",
  props:{
    article_content:{
      type:String,
      required:true
    }
  },
  computed:{
    content:{
      get:function() {
        return this.article_content;
      },
      set:function(value) {
        this.$emit('input', value);

      }
    },
    wordcount:{
      get:function (){
        return countCharacters(this.content)
      }
    }
  },
  data (){
    return{
      editorZIndex:8
    }
  },
  methods: {
    uploadImg(pos, file) {
      // 无论是点击上传图片还是直接在编辑栏中粘贴 都会触发
      //this.article.articleCover = this.$refs.imgUpload.url

      var formData = new FormData();
      formData.append('image', file);
      uploadImage(formData).then(res => {
        if (res.code === 200) {
          this.$refs.md.$img2Url(pos, res.data)
        } else {
          this.errorMsg('图片上传失败' + res.message)
        }
      }).catch(() => {
        this.errorMsg('图片上传请求出错')
      })
    },
    fullScreen(){
      if (this.editorZIndex===8){
        this.editorZIndex=1005
      }else this.editorZIndex=8
    }
  }
}
</script>

<style scoped>

</style>
