<template>
  <div class="app-container">

    <!--添加-->
    <el-form inline>
      <el-form-item>
        <el-button type="primary" size="small" icon="el-icon-plus" @click="addDialogVisible=true">添加友链</el-button>
      </el-form-item>
      <el-form-item style="margin-left: 20px">
        <el-switch v-model="infoForm.commentEnable" active-text="页面评论" @change="commentEnabledChanged"></el-switch>
      </el-form-item>
    </el-form>

    <el-table :data="friendList">
      <el-table-column label="序号" type="index" width="50"></el-table-column>
      <el-table-column label="头像" width="80">
        <template v-slot="scope">
          <el-avatar shape="square" :size="60" fit="contain" :src="scope.row.avatar"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column label="昵称" prop="nickname"></el-table-column>
      <el-table-column label="描述" prop="description"></el-table-column>
      <el-table-column label="站点" prop="websiteUrl"></el-table-column>
      <el-table-column label="是否公开" width="100">
        <template v-slot="scope">
          <el-switch v-model="scope.row.published" @change="friendPublishedChanged(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="浏览次数" prop="views" width="100"></el-table-column>
      <el-table-column label="创建时间" width="170">
        <template v-slot="scope">{{ scope.row.createTime | dateFormat }}</template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template v-slot="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row)">编辑</el-button>
          <el-popconfirm title="确定删除吗？" icon="el-icon-delete" iconColor="red" @onConfirm="deleteFriendById(scope.row.id)">
            <el-button size="mini" type="danger" icon="el-icon-delete" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum"
                   :page-sizes="[10, 20, 30, 50]" :page-size="queryInfo.pageSize" :total="total"
                   layout="total, sizes, prev, pager, next, jumper" background>
    </el-pagination>

    <!--友链页面信息-->
    <el-form label-position="top">
      <el-form-item label="友链页面信息">
<!--        <mavon-editor v-model="infoForm.content"/>-->
        <SimplifyMarkdownEditor v-model="infoForm.content" :article_content="infoForm.content"></SimplifyMarkdownEditor>
      </el-form-item>
      <el-form-item style="text-align: right;">
        <el-button type="primary" icon="el-icon-check" @click="updateContent">保存</el-button>
      </el-form-item>
    </el-form>

    <!--添加友链对话框-->
    <el-dialog title="添加友链" width="40%" :visible.sync="addDialogVisible" :close-on-click-modal="false" @close="addDialogClosed">
      <!--内容主体-->
      <el-form :model="addForm" :rules="formRules" ref="addFormRef" label-width="80px">
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="addForm.nickname"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="addForm.description"></el-input>
        </el-form-item>
        <el-form-item label="网站" prop="websiteUrl">
          <el-input v-model="addForm.websiteUrl"></el-input>
        </el-form-item>
        <el-form-item label="头像URL" prop="avatar">
          <el-input v-model="addForm.avatar"></el-input>
        </el-form-item>
        <el-form-item label="是否公开" prop="published">
          <el-switch v-model="addForm.published"></el-switch>
        </el-form-item>
      </el-form>
      <!--底部-->
      <span slot="footer">
				<el-button @click="addDialogVisible=false">取 消</el-button>
				<el-button type="primary" @click="saveFriend">确 定</el-button>
			</span>
    </el-dialog>

    <!--编辑友链对话框-->
    <el-dialog title="编辑友链" width="40%" :visible.sync="editDialogVisible" :close-on-click-modal="false" @close="editDialogClosed">
      <!--内容主体-->
      <el-form :model="editForm" :rules="formRules" ref="editFormRef" label-width="80px">
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="editForm.nickname"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="editForm.description"></el-input>
        </el-form-item>
        <el-form-item label="网站" prop="websiteUrl">
          <el-input v-model="editForm.websiteUrl"></el-input>
        </el-form-item>
        <el-form-item label="头像URL" prop="avatar">
          <el-input v-model="editForm.avatar"></el-input>
        </el-form-item>
        <el-form-item label="是否公开" prop="published">
          <el-switch v-model="editForm.published"></el-switch>
        </el-form-item>
      </el-form>
      <!--底部-->
      <span slot="footer">
				<el-button @click="editDialogVisible=false">取 消</el-button>
				<el-button type="primary" @click="editFriend">确 定</el-button>
			</span>
    </el-dialog>
  </div>
</template>

<script>
import {
  getFriendsByQuery, updatePublished, saveFriendItem, updateFriendItem,
  deleteFriendItemById, getFriendInfo, updateContent, updateCommentEnabled
} from "@/api/friend";
import SimplifyMarkdownEditor from "@/components/SimplifyMarkdownEditor";

export default {
  name: "FriendList",
  components: {SimplifyMarkdownEditor},
  data() {
    return {
      infoForm: {
        content: '',
        commentEnable: true,
      },
      queryInfo: {
        pageNum: 1,
        pageSize: 10
      },
      friendList: [],
      total: 0,
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {
        nickname: '',
        description: '',
        websiteUrl: '',
        avatar: '',
        published: true
      },
      editForm: {
        nickname: '',
        description: '',
        websiteUrl: '',
        avatar: '',
        published: true
      },
      formRules: {
        nickname: [{required: true, message: '请输入昵称', trigger: 'blur'}],
        description: [{required: true, message: '请输入描述', trigger: 'blur'}],
        websiteUrl: [{required: true, message: '请输入网站', trigger: 'blur'}],
        avatar: [{required: true, message: '请输入头像URL', trigger: 'blur'}],
      }
    }
  },
  created() {
    this.getFriendList()
    this.getInfo()
  },
  methods: {
    getInfo() {
      getFriendInfo().then(res => {
        if (res.code === 200) {
          this.successMsg(res.message)
          this.infoForm = res.data
        } else {
          this.errorMsg(res.message)
        }
      }).catch(() => {
        this.errorMsg("请求失败")
      })
    },
    updateContent() {
      updateContent(this.infoForm.content).then(res => {
        if (res.code === 200) {
          this.successMsg(res.message)
          this.getInfo()
        } else {
          this.errorMsg(res.message)
        }
      }).catch(() => {
        this.errorMsg("请求失败")
      })
    },
    commentEnabledChanged() {
      updateCommentEnabled(this.infoForm.commentEnable).then(res => {
        if (res.code === 200) {
          this.successMsg(res.message)
        } else {
          this.errorMsg(res.message)
        }
      }).catch(() => {
        this.errorMsg("请求失败")
      })
    },
    getFriendList() {
      getFriendsByQuery(this.queryInfo).then(res => {
        if (res.code === 200) {
          this.friendList = res.data.list
          this.total = res.data.total
          this.successMsg(res.message)
        } else {
          this.errorMsg(res.message)
        }
      }).catch(() => {
        this.errorMsg("请求失败")
      })
    },
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize
      this.getFriendList()
    },
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage
      this.getFriendList()
    },
    friendPublishedChanged(row) {
      updatePublished(row.id, row.published).then(res => {
        if (res.code === 200) {
          this.successMsg(res.message)
        } else {
          this.errorMsg(res.message)
        }
      }).catch(() => {
        this.errorMsg("请求失败")
      })
    },
    deleteFriendById(id) {
      deleteFriendItemById(id).then(res => {
        if (res.code === 200) {
          this.getFriendList()
          this.successMsg(res.message)
        } else {
          this.errorMsg(res.message)
        }
      }).catch(() => {
        this.errorMsg("请求失败")
      })
    },
    showEditDialog(row) {
      this.editForm = {...row}
      this.editDialogVisible = true
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },
    editDialogClosed() {
      this.editForm = {}
      this.$refs.editFormRef.resetFields()
    },
    saveFriend() {
      this.$refs.addFormRef.validate(valid => {
        if (valid) {
          saveFriendItem(this.addForm).then(res => {
            if (res.code === 200) {
              this.getFriendList()
              this.successMsg(res.message)
              this.addDialogVisible = false
            } else {
              this.errorMsg(res.message)
            }
          }).catch(() => {
            this.errorMsg("请求失败")
          })
        }
      })
    },
    editFriend() {
      this.$refs.editFormRef.validate(valid => {
        if (valid) {
          updateFriendItem(this.editForm).then(res => {
            if (res.code === 200) {
              this.getFriendList()
              this.successMsg(res.message)
              this.editDialogVisible = false
            } else {
              this.errorMsg(res.message)
            }
          }).catch(() => {
            this.errorMsg("请求失败")
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.el-button + span {
  margin-left: 10px;
}

.el-form {
  margin-top: 15px !important;
}

.el-form--inline .el-form-item {
  margin-bottom: 0;
}
</style>
