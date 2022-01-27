<template>
  <div>

    <!--标题输入框-->
    <el-row type="flex"
            justify="center">
      <el-col :span="21">
        <el-input v-model="article.articleTitle"
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
    <el-row style="height: calc(100vh - 140px);">
      <mavon-editor v-model="article.articleContent"
                    style="height: auto;"
                    fontSize="16px">
      </mavon-editor>

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
          <el-input v-model="article.firstPicture"
                    autocomplete="off"
                    placeholder="图片 URL"></el-input>
          <img-upload @onUpload="uploadImg"
                      ref="imgUpload"
                      style="margin-top: 5px"></img-upload>
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
              <el-select v-model="article.category"
                         placeholder="请选择分类（输入可添加新分类）"
                         :allow-create="true"
                         :filterable="true"
                         style="width: 100%;">
                <el-option :label="item.name"
                           :value="item.id"
                           v-for="item in categoryList"
                           :key="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <!--选择标签-->
          <el-col :span="12">
            <el-form-item label="标签"
                          prop="tagList">
              <el-select v-model="article.articleTags"
                         placeholder="请选择标签（输入可添加新标签）"
                         :allow-create="true"
                         :filterable="true"
                         :multiple="true"
                         style="width: 100%;">
                <el-option :label="item.tagName"
                           :value="item.tagId"
                           v-for="item in tagList"
                           :key="item.tagId"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-button @click="saveArticles"
                 type="primary">保存</el-button>
    </el-dialog>

  </div>
</template>

<script>
import ImgUpload from './ImgUpload'
import Breadcrumb from '@/adminView/dashboard/Breadcrumb.vue'
import {getAllTags} from '../../api/tag'
import {getAllCategories} from '../../api/category'

export default {
  name: 'Editor',
  components: {
    ImgUpload,
    Breadcrumb
  },
  data () {
    return {
      dialogVisible: false,
      categoryList: [{ id: 1, name: '生活随笔' }, { id: 2, name: '人生感悟' }, { id: 3, name: '学习技术' }],
      tagList: [{ name: 'springboot', id: 'springboot' }, { name: 'springboot2', id: 'springboot2' }, { name: 'springboot3', id: 'springboot3' }],
      article: {
        // 文章标题
        articleTitle: '',
        // 文章首图
        firstPicture: '',
        // 文章描述
        description: '',
        // 文章正文
        articleContent: '',
        // 文章分类
        category: '',
        // 文章标签
        articleTags: [],
        // 文章字数
        words: 0,
        // 文章观看量
        views: 0,
        // 是否置顶
        top: false,
        // 是否公开
        published: true
      },
      formRules: {
        title: [{ required: true, message: '请输入标题', trigger: 'change' }],
        category: [{ required: true, message: '请选择分类', trigger: 'change' }],
        tag: [{ required: true, message: '请选择标签', trigger: 'change' }]
      }
    }
  },
  mounted () {
    if (this.$route.params.article) {
      this.article = this.$route.params.article
    }
  },
  created () {
    this.getData()
  },
  methods: {
    saveArticles () {
      // value 是 md，render 是 html
      this.$confirm('是否保存并发布文章?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios
          .post('/admin/saveBlog', {
            ...this.article
          }).then(resp => {
            if (resp && resp.data.code === 200) {
              this.successMsg(resp.data.data + '已保存成功')
            } else {
              this.errorMsg(resp.data.message)
            }
          })
        this.dialogVisible = false
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消发布'
        })
      })
    },
    uploadImg () {
      this.article.articleCover = this.$refs.imgUpload.url
    },
    getData () {
      getAllTags().then(res => {
        if (res.data.code === 200) {
          this.tagList = res.data.data
        } else {
          this.errorMsg(res.data.message)
        }
      })
      getAllCategories().then(res => {
        if (res.data.code === 200) {
          this.categoryList = res.data.data
        } else {
          this.errorMsg(res.data.message)
        }
      })
    }
  }
}
</script>
