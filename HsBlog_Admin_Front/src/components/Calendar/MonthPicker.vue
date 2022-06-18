<template>
  <!-- 月份选择器，支持左右箭头修改月份 -->
  <div class="month-con">
    <i class="el-icon-arrow-left" @click="reduceMonth()" />
    <span class="month"
    >{{ time.year }}年{{
        time.month + 1 > 9 ? time.month + 1 : '0' + (time.month + 1)
      }}月</span
    >
    <i class="el-icon-arrow-right" @click="addMonth()" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      time: {
        year: new Date().getFullYear(),
        month: new Date().getMonth()
      }
    }
  },
  created() {},
  methods: {
    reduceMonth() {
      if (this.time.month > 0) {
        this.time.month = this.time.month - 1
      } else {
        this.time.month = 11
        this.time.year = this.time.year - 1
      }
      this.$emit('changeMonth', this.time)
    },
    addMonth() {
      if (this.time.month < 11) {
        this.time.month = this.time.month + 1
      } else {
        this.time.month = 0
        this.time.year = this.time.year + 1
      }
      this.$emit('changeMonth', this.time)
    }
  }
}
</script>
<style lang="less" scoped>
.month-con {
  font-weight: 700;
  font-size: 18px;
  .month {
    margin: 0 10px;
  }
}
</style>
