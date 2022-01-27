<template>
  <div>
    <sui-grid :columns="4"
            class="mycard">
      <el-card shadow="always"
               :body-style="{ padding: '0px',background_color:'rgba(34,36,38,.1)' }">
        <img src="@/assets/changyue.jpg"
             class="image">
        <div style="padding: 14px;">
          <span>好吃的汉堡</span>
          <div class="bottom clearfix">
            <time class="time">{{ currentDate }}</time>
            <el-button type="text"
                       class="button">操作按钮</el-button>
          </div>
        </div>
      </el-card>
    </sui-grid>
  </div>
</template>
<script>
export default {
  name: 'myCard',
  data () {
    return {
      currentDate: new Date()
    }
  }
}
</script>
<style>
.mycard {
  padding: 40px;
}
.image {
  width: 100%;
  display: block;
}
</style>
