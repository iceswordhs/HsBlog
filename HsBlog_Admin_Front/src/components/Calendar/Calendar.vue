<template>
  <div>
    <el-row>
      <!-- 左侧，提示早班、晚班或者上午、下午 -->
      <el-col :span="2">
        <div class="date-con tip-con">
          <el-col
              v-for="item in 6"
              :key="item"
              class="date thisMonth tip"
              :span="1"
          >
            <div class="morning">早上</div>
            <div class="evening">晚上</div>
          </el-col>
        </div>
      </el-col>
      <!-- 右侧，周一到周五具体内容 -->
      <el-col :span="22">
            <span class="month"
            >{{ time.year }}年{{
                time.month + 1 > 9 ? time.month + 1 : '0' + (time.month + 1)
              }}月</span
            >
        <!-- 第一行表头，周一到周日 -->
        <div class="top-con">
          <div class="top" v-for="item in top" :key="item">星期{{ item }}</div>
        </div>
        <!-- 日历号 -->
        <div class="date-con">
          <div
              class="date"
              :class="[item.thisMonth, item.isToday, item.afterToday]"
              v-for="(item, index) in visibleCalendar"
              :key="index"
          >
            <div>{{ item.day }}</div>
            <div class="morning" v-if="statusList[index]!==undefined">{{statusList[index].morningStatus===0?'未打卡':'已打卡'}}<el-button @click="sign(item.patternTime,1)" v-if="item.isToday&&statusList[index].morningStatus===0" type="success" size="mini" icon="el-icon-check" circle/></div>

            <div class="evening" v-if="statusList[index]!==undefined">{{statusList[index].nightStatus===0?'未打卡':'已打卡'}}<el-button @click="sign(item.patternTime,2)" v-if="item.isToday&&statusList[index].nightStatus===0" type="success" size="mini" icon="el-icon-check" circle/></div>

          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// import utils from './utils.js'
import {getSignStatusData,sign} from '@/api/sign'
// import Vue from "vue";
export default {
  props: {
    // time包含年份和月份  每次向后端请求该月的数据List集合
    time: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  data() {
    return {
      top: ['一', '二', '三', '四', '五', '六', '日'],
      statusList:[]
    }
  },
  created() {
    this.getData()
  },
  watch:{
    time(){
      this.getData()
    }
  },
  methods: {
    // 获取
    sign(today,type){
      sign(today,type)
      this.getData()
      // var text=document.getElementsByClassName('isToday').item(0).getElementsByClassName(className).item(0);
      // console.log(text)
      // text.innerHTML=text.innerHTML+'完成'
    },
    getData(){
      const today = new Date()
      today.setHours(0)
      today.setMinutes(0)
      today.setSeconds(0)
      today.setMilliseconds(0)

      // 获取当前月份第一天
      const currentFirstDay = new Date(this.time.year, this.time.month, 1)
      // 获取第一天是周几
      const weekDay = currentFirstDay.getDay()
      // 用当前月份第一天减去周几前面几天，就是看见的日历的第一天
      const startDay = currentFirstDay - (weekDay - 1) * 24 * 3600 * 1000
      const date = new Date(startDay)
      getSignStatusData(date.toISOString().slice(0,10)).then(res=>{
        // var array=JSON.parse(JSON.stringify(res.data))
        console.log(res.data)
        // for (var i=0;i<42;i++)
        // this.statusList.push(res.data[i])
        this.statusList=res.data
        console.log('等于res.data',JSON.parse(JSON.stringify(this.statusList)))
      })
    }
  },
  computed: {
    // 获取当前月份显示日历,共42天
    visibleCalendar:function() {
      // 获取今天的年月日
      const today = new Date()
      today.setHours(0)
      today.setMinutes(0)
      today.setSeconds(0)
      today.setMilliseconds(0)
      const calendarArr = []
      // 获取当前月份第一天
      const currentFirstDay = new Date(this.time.year, this.time.month, 1)
      // 获取第一天是周几
      const weekDay = currentFirstDay.getDay()
      // 用当前月份第一天减去周几前面几天，就是看见的日历的第一天
      const startDay = currentFirstDay - (weekDay - 1) * 24 * 3600 * 1000
      // 我们统一用42天来显示当前显示日历
      for (let i = 0; i < 42; i++) {
        const date = new Date(startDay + i * 24 * 3600 * 1000)
        calendarArr.push({
          date: new Date(startDay + i * 24 * 3600 * 1000),
          year: date.getFullYear(),
          month: date.getMonth(),
          day: date.getDate(),
          // 是否在当月
          thisMonth:
              date.getFullYear() === today.getFullYear() &&
              date.getMonth() === today.getMonth()
                  ? 'thisMonth'
                  : '',
          // 是否是今天
          isToday:
              date.getFullYear() === today.getFullYear() &&
              date.getMonth() === today.getMonth() &&
              date.getDate() === today.getDate()
                  ? 'isToday'
                  : '',
          // 是否在今天之后
          afterToday: date.getTime() >= today.getTime() ? 'afterToday' : '',
          patternTime:date.toISOString().slice(0,10)
        })
      }
      return calendarArr
    }
  }
}
</script>
<style lang="less" scoped>
.top-con {
  display: flex;
  align-items: center;
  .top {
    width: 14.285%;
    background-color: rgb(242, 242, 242);
    padding: 10px 0;
    margin: 5px;
    text-align: center;
  }
}
.date-con {
  display: flex;
  flex-wrap: wrap;
  .date {
    width: 14.285%;
    text-align: center;
    padding: 5px;
    .morning {
      padding: 10px 0;
      background-color: rgba(220, 245, 253, 0.3);
    }
    .evening {
      padding: 10px 0;
      background-color: rgba(220, 244, 209, 0.3);
    }
  }
  .thisMonth {
    .morning {
      background-color: rgb(220, 245, 253);
    }
    .evening {
      background-color: rgb(220, 244, 209);
    }
  }
  .isToday {
    font-weight: 700;
    .morning {
      background-color: rgb(169, 225, 243);
    }
    .evening {
      background-color: rgb(193, 233, 175);
    }
  }
}
.tip-con {
  margin-top: 51px;
  .tip {
    margin-top: 21px;
    width: 100%;

  }
}
</style>
