<template>
  <div class="app-container">

    <el-form :model="form" :rules="formRules" ref="formRef" label-position="top">
      <el-form-item label="标题" prop="title" style="width: 50%">
        <el-input v-model="form.title" placeholder="请输入标题"></el-input>
      </el-form-item>

      <el-row :gutter="20" style="width: 50%">
        <el-col>
          <el-form-item label="评论开关">
            <el-switch v-model="form.commentEnabled" active-text="评论"></el-switch>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="正文" prop="content">
        <SimplifyMarkdownEditor :article_content="form.content" v-model="form.content"></SimplifyMarkdownEditor>
      </el-form-item>

      <el-form-item style="text-align: right;">
        <el-button type="primary" icon="el-icon-check" @click="submit">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import SimplifyMarkdownEditor from "@/components/SimplifyMarkdownEditor";
import {getAbout,updateAbout} from "@/api/about";

export default {
  name: "About",
  components:{SimplifyMarkdownEditor},
  data(){
    return{
      about:{
        title:'',
        content:'',
        commentEnable: false
      },
      form: {
        title: '',
        content: '',
        commentEnabled: false
      },
      formRules: {
        title: [{required: true, message: '请输入标题', trigger: 'change'}],
      }
    }

  },
  created() {
      this.getAbout()
  },
  methods:{
    getAbout(){
      getAbout().then(resp =>{
        if(resp.code===200){
          this.form=resp.data
        }else {
          this.errorMsg(resp.message)
        }
      }).catch(() =>{
        this.errorMsg('获取关于我信息请求失败')
      })
    },
    submit() {
      this.$refs.formRef.validate(valid => {
        if (valid) {
          // //纯数字
          // const reg = /^\d{1,}$/
          // if (!reg.test(this.form.musicId)) {
          //   return this.er("歌曲ID有误")
          // }
          updateAbout(this.form).then(res => {
            if (res.code === 200) {
              this.successMsg(res.message)
            } else {
              this.errorMsg(res.message)
            }
          }).catch(() => {
            this.errorMsg("请求失败")
          })
        } else {
          return this.errorMsg('请填写必要的表单')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
