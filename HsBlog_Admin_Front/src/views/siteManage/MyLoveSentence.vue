<template>
  <div class="app-container" style="margin-top: 50px">
    <el-row :gutter="20" type="flex" justify="center">
      <el-col :span="22">
        <el-card>
          <div slot="header" style="text-align: center">
            <span>那些需要时刻谨记的句子</span>
          </div>
          <el-form label-position="right" label-width="200px">
            <el-form-item v-for="item in sentences" :key="item.id">
              <div>
                <el-col :span="3">
                  <el-input v-model="item.nameZh"></el-input>
                </el-col>
                <el-col :span="17">
                  <el-input type="text" autosize maxlength="60"
                            show-word-limit v-model="item.value"></el-input>
                </el-col>
                <el-col :span="4">
                  <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteFavorite(item)">删除</el-button>
                </el-col>
              </div>
            </el-form-item>
            <el-row :gutter="20" type="flex" justify="center">
              <el-col :push="9">
                <el-button type="primary" size="mini" icon="el-icon-plus" @click="addFavorite">添加语录</el-button>
              </el-col>
              <el-col :push="1">
                <el-button type="primary" size="mini" icon="el-icon-check" @click="submit">保  存</el-button>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getSentences, updateSentence} from "@/api/siteSetting";

export default {
  name: "MyLoveSentence",
  data() {
    return {
      deleteIds: [],
      sentences: [{id:1,nameZh:'自定义',nameEn:'xxx',value:'xxxxx'},{id:2,nameZh:'xxx',nameEn:'xxx',value:'xxxxx'},{id:3,nameZh:'xxx',nameEn:'xxx',value:'xxxxx'}],
    }
  },
  created() {
    this.getData()
  },
  methods: {
    getData() {
      getSentences().then(res => {
        if (res.code === 200) {
          this.sentences = res.data
          this.successMsg(res.message)
        } else {
          this.errorMsg(res.message)
        }
      }).catch(() => {
        this.errorMsg("请求失败")
      })
    },
    addFavorite() {
      this.sentences.push({
        nameEn: "MyLoveSentence",
        nameZh: "iceSword",
        type: 4,
        value: "我喜欢的格言警句"
      })
    },
    deleteFavorite(favorite) {
      let arr = this.sentences
      if (favorite.id) {
        this.deleteIds.push(favorite.id)
        arr.forEach((item, index) => {
          if (item.id === favorite.id) {
            arr.splice(index, 1)
            return
          }
        })
      } else {
        arr.forEach((item, index) => {
          if (item.key === favorite.key) {
            arr.splice(index, 1)
            return
          }
        })
      }
    },
    submit() {
      updateSentence(this.sentences, this.deleteIds).then(res => {
        if (res.code === 200) {
          this.deleteIds = []
          this.getData()
          this.successMsg(res.message)
        } else {
          this.errorMsg(res.message)
        }
      }).catch(() => {
        this.errorMsg("请求失败")
      })
    }
  }
}
</script>

<style scoped>

</style>
