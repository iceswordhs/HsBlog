<template>
  <div class="app-container">

    <!--标题输入框-->
    <el-row >
      <el-col :span="21">
        <el-input v-model="article.title"
                  style="margin: 20px 0px 20px 0px;font-size: 18px;"
                  placeholder="请输入标题"></el-input>
      </el-col>
      <el-col :span="3">
        <el-button @click="dialogVisible=true"
                   style="margin: 20px 0px 20px 50px;"
                   type="primary">保存</el-button>
      </el-col>
    </el-row>

    <!--编辑器主体-->
    <el-row>
<!--      <mavon-editor v-model="article.content"-->
<!--                    fontSize="16px"-->
<!--                    :style="{'z-index':editorZIndex}"-->
<!--                    @imgAdd="uploadImg"-->
<!--                    @fullScreen="fullScreen"-->
<!--                    ref="md">-->
<!--      </mavon-editor>-->
          <MarkdownEditor :article_content="article.content"
                          v-model="article.content"
                          ref="editor">

          </MarkdownEditor>
    </el-row>

    <!--摘要和封面弹框-->
    <el-dialog :visible.sync="dialogVisible"
               width="50%">
      <el-form :model="article"
               :rules="formRules"
               ref="formRef"
               label-position="top">
        <el-divider content-position="left">摘要</el-divider>
        <el-input type="textarea"
                  v-model="article.description"
                  rows="6"
                  maxlength="255"
                  show-word-limit>
        </el-input>
        <el-divider content-position="left">封面</el-divider>
        <div style="margin-top: 20px">
          <el-input v-model="article.homePicture"
                    autocomplete="off"
                    placeholder="图片 URL"></el-input>
<!--          <img-upload @onUpload="uploadImg"-->
<!--                      ref="imgUpload"-->
<!--                      style="margin-top: 5px"></img-upload>-->
        </div>
        <span slot="footer"
              class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary"
                     @click="dialogVisible = false">确 定</el-button>
        </span>
        <el-row :gutter="20">
          <!--选择分类-->
          <el-col :span="12">
            <el-form-item label="分类"
                          prop="categoryList">
              <el-select v-model="article.category.name"
                         placeholder="请选择分类（输入可添加新分类）"
                         :allow-create="true"
                         :filterable="true"
                         style="width: 100%;">
                <el-option :label="item.name"
                           :value="item.name"
                           v-for="item in categoryList"
                           :key="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <!--选择标签-->
          <el-col :span="12">
            <el-form-item label="标签"
                          prop="tagList">
              <el-select v-model="article.tags"
                         placeholder="请选择标签（输入可添加新标签）"
                         :allow-create="true"
                         :filterable="true"
                         :multiple="true"
                         style="width: 100%;">
                <el-option :label="item.tagName"
                           :value="item.tagName"
                           v-for="item in tagList"
                           :key="item.tagId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" justify="space-between">
          <el-col>
            <el-switch v-model="article.commentEnable" active-text="评论"></el-switch>
          </el-col>
          <el-col>
            <el-switch v-model="article.top" active-text="置顶"></el-switch>
          </el-col>
          <el-col>
            <el-switch v-model="article.published" active-text="公开"></el-switch>
          </el-col>
        </el-row>
        <el-row type="flex" justify="center" :gutter="20" style="margin-top: 20px">
          <el-button @click="saveArticles"
                     type="primary" style="text-align: center;">保存</el-button>
        </el-row>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import ImgUpload from './ImgUpload'
import MarkdownEditor from "@/components/MarkdownEditor";
import {getAllTags} from '@/api/tag'
import {getAllCategories} from '@/api/category'
import {saveBlog,getBlogById} from "@/api/blog"
// import {uploadImage} from "@/api/uploadImage";

export default {
  name: 'Editor',
  components: {
    // eslint-disable-next-line
    ImgUpload,
    MarkdownEditor
  },
  data () {
    return {
      dialogVisible: false,
      categoryList: [{ id: 1, name: '生活随笔' }, { id: 2, name: '人生感悟' }, { id: 3, name: '学习技术' }],
      tagList: [{ name: 'springboot', tagId: 'springboot' }, { name: 'springboot2', tagId: 'springboot2' }, { name: 'springboot3', tagId: 'springboot3' }],
      article: {
        // 文章标题
        title: '',
        // 文章首图
        homePicture: '',
        // 文章描述
        description: '',
        // 文章正文
        content: '',
        // 文章分类
        category: {
          id:0,
          name:''
        },
        // 文章标签
        tags: [],
        // 文章字数
        words: 0,
        // 文章观看量
        views: 0,
        // 是否置顶
        top: false,
        // 是否公开
        published: true,
        //是否开启评论,
        commentEnable:true
      },
      formRules: {
        title: [{ required: true, message: '请输入标题', trigger: 'change' }],
        category: [{ required: true, message: '请选择分类', trigger: 'change' }],
        tag: [{ required: true, message: '请选择标签', trigger: 'change' }]
      },
      // editorZIndex:8
    }
  },
  mounted () {
    if (this.$route.params.article) {
      this.article = this.$route.params.article
    }
  },
  created () {
    this.getData()
    if (this.$route.params.id) {
      this.getBlog(this.$route.params.id)
    }
  },
  methods: {
    saveArticles () {
      this.article.words=this.$refs.editor.wordcount

      // value 是 md，render 是 html
      this.$confirm('是否保存并发布文章?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        saveBlog(this.article).then(resp => {
            if (resp && resp.code === 200) {
              this.successMsg(resp.data + '已保存成功')
            } else {
              this.errorMsg(resp.message)
            }
          })
        this.dialogVisible = false
      }).catch(() => {
        this.infoMsg('已取消发布')
      })
    },
    getBlog(id) {
      getBlogById(id).then(res => {
        if (res.code === 200) {
          this.computeCategoryAndTag(res.data)
          this.article = res.data
          // this.radio = this.form.published ? (this.form.password !== '' ? 3 : 1) : 2
          this.successMsg(res.message)
        } else {
          this.errorMsg(res.message)
        }
      }).catch(() => {
        this.errorMsg('请求失败')
      })
    },
    computeCategoryAndTag(blog) {
      blog.cate = blog.category.id
      blog.tagList = []
      blog.tags.forEach(item => {
        blog.tagList.push(item.id)
      })
    },
    // uploadImg (pos,file) {
    //   // 无论是点击上传图片还是直接在编辑栏中粘贴 都会触发
    //   //this.article.articleCover = this.$refs.imgUpload.url
    //
    //   var formData=new FormData();
    //   formData.append('image',file);
    //   uploadImage(formData).then(res => {
    //     if (res.code === 200) {
    //       this.$refs.md.$img2Url(pos,res.data)
    //     }else {
    //       this.errorMsg('图片上传失败'+res.message)
    //     }
    //   }).catch(() => {
    //     this.errorMsg('图片上传请求出错')
    //   })
    // },
    getData () {
      getAllTags().then(res => {
        if (res.code === 200) {
          this.tagList = res.data
        } else {
          this.errorMsg(res.message)
        }
      })
      getAllCategories().then(res => {
        if (res.code === 200) {
          this.categoryList = res.data
        } else {
          this.errorMsg(res.message)
        }
      })
    }
    // ,
    // fullScreen(){
    //   if (this.editorZIndex===8){
    //     this.editorZIndex=1005
    //   }else this.editorZIndex=8
    // }
  }
}
</script>
