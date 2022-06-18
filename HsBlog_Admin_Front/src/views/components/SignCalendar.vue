<template>
  <div>
    <el-card>
      <!-- 操作栏 -->
      <el-row type="flex" justify="space-around">
        <el-col :span="12">
          <!-- 月份选择器 -->
          <monthpicker @changeMonth="changeMonth"></monthpicker>
        </el-col>
        <el-col :span="12"></el-col>
      </el-row>
      <!-- 日历栏 -->
      <el-row class="calendar-con">
        <calendar :time="time"></calendar>
      </el-row>
    </el-card>
  </div>
</template>

<script>
// 月份选择器
import MonthPicker from '@/components/Calendar/MonthPicker.vue'
// 日历组件
import Calendar from '@/components/Calendar/Calendar.vue'
export default {
  components: {
    monthpicker: MonthPicker,
    calendar: Calendar
  },
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
    // 修改月份
    changeMonth(time) {
      console.log('time', time)
      this.time = time
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
.calendar-con {
  margin-top: 20px;
}
</style>
