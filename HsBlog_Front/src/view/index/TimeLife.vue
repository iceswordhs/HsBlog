<template>
  <div class="ui segments m-box">
    <div class="ui secondary segment"><i class="tags icon"></i>人生倒计时</div>
    <div class="ui yellow segment m-padding-small progressbar">
      <div>
        <div class="title">今日已经过去了{{hours}}小时</div>
        <el-progress class="progress0" :stroke-width="10" :percentage='percentDay'></el-progress>
      </div>

      <div>
        <div class="title">这周已经过去了{{daysOfWeek}}天</div>
        <el-progress class="progress1" :stroke-width="10" :percentage='percentWeek'></el-progress>
      </div>

      <div>
        <div class="title">{{mouth}}月已经过去了{{daysOfMouth}}天</div>
        <el-progress class="progress2" :stroke-width="10" :percentage='percentMouth' ></el-progress>
      </div>

      <div>
        <div class="title">{{year}}年已经过去了{{days}}天</div>
        <el-progress class="progress3" :stroke-width="10" :percentage='percentYear' ></el-progress>
      </div>

      <div>
        <div class="title">听老师的话好好学习 已过{{pastDays}}天 共1173天</div>
        <el-progress class="progress3" :stroke-width="10" :percentage='percentTeacherAdmonitionDays' ></el-progress>
      </div>
    </div>
  </div>
</template>
<script>
import moment from 'moment'

export default {
  name: 'TimeLife',
  data () {
    return {
      // customColor: '#EA5A49'
      //       -webkit-repeating-linear-gradient(-30deg, #83a7cf 0, #83a7cf 10px,#93b3d6 10px, #93b3d6 20px)
      // customColor1: '#ffd980 linear-gradient(135deg, #f7ba2a 25%, transparent 25%, transparent 50%, #f7ba2a 50%, #f7ba2a 75%, transparent 75%, transparent 100%)'
    }
  },
  mounted () {
    this.styleInitialize()
  },
  methods: {
    isLeapYear () {
      const year = new Date().getFullYear()
      if (year % 400 === 0) {
        return true
      } else if (year % 4 === 0 && year % 100 !== 0) {
        return true
      } else {
        return false
      }
    },
    getDayOfYear () {
      return this.isLeapYear() ? 366 : 365
    },
    getDayOfMouth () {
      return new Date(new Date().getFullYear(), new Date().getMonth() + 1, 0).getDate()
    },
    styleInitialize () {
      document.getElementsByClassName('el-progress-bar__inner')[0].style.background = '#bde6ff linear-gradient(135deg, #50bfff 25%, transparent 25%, transparent 50%, #50bfff 50%, #50bfff 75%, transparent 75%, transparent 100%)'
      document.getElementsByClassName('el-progress-bar__inner')[0].style.backgroundSize = '30px 30px'
      document.getElementsByClassName('el-progress-bar__inner')[1].style.background = '#67c23a linear-gradient(135deg, #4f9e28 25%, transparent 25%, transparent 50%, #4f9e28 50%, #4f9e28 75%, transparent 75%, transparent 100%)'
      document.getElementsByClassName('el-progress-bar__inner')[1].style.backgroundSize = '30px 30px'
      document.getElementsByClassName('el-progress-bar__inner')[2].style.background = '#ffa9a9 linear-gradient(135deg, #ff4949 25%, transparent 25%, transparent 50%, #ff4949 50%, #ff4949 75%, transparent 75%, transparent 100%)'
      document.getElementsByClassName('el-progress-bar__inner')[2].style.backgroundSize = '30px 30px'
      document.getElementsByClassName('el-progress-bar__inner')[3].style.background = '#ffd980 linear-gradient(135deg, #f7ba2a 25%, transparent 25%, transparent 50%, #f7ba2a 50%, #f7ba2a 75%, transparent 75%, transparent 100%)'
      document.getElementsByClassName('el-progress-bar__inner')[3].style.backgroundSize = '30px 30px'
      document.getElementsByClassName('el-progress-bar__inner')[4].style.background = '#ffd980 linear-gradient(135deg, #f7ba2a 25%, transparent 25%, transparent 50%, #f7ba2a 50%, #f7ba2a 75%, transparent 75%, transparent 100%)'
      document.getElementsByClassName('el-progress-bar__inner')[4].style.backgroundSize = '30px 30px'
    }
  },
  computed: {
    hours () {
      return new Date().getHours()
    },
    year () {
      return new Date().getFullYear()
    },
    mouth () {
      return new Date().getMonth() + 1
    },
    days () {
      let start = new Date()
      start.setMonth(0)
      start.setDate(1)
      // start就是今年第一天
      // 今天的时间戳 减去今年第一天的时间戳
      let offset = new Date().getTime() - start.getTime()
      return parseInt(offset / 1000 / 60 / 60 / 24) + 1
    },
    pastDays () {
      return moment().diff(moment('2025-03-30'), 'days')
    },
    daysOfWeek () {
      return new Date().getDay() === 0 ? 7 : new Date().getDay()
    },
    daysOfMouth () {
      return new Date().getDate()
    },
    percentDay () {
      return parseInt(this.hours * 100 / 24)
    },
    percentWeek () {
      return parseInt((new Date().getDay() || 7) * 100 / 7)
    },
    percentMouth () {
      return parseInt(new Date().getDate() * 100 / this.getDayOfMouth())
    },
    percentYear () {
      return parseFloat((this.days * 100 / this.getDayOfYear()).toFixed(1))
    },
    percentTeacherAdmonitionDays () {
      let allDays = 1173
      return parseFloat((moment().diff(moment('2025-03-30'), 'days') * 100 / allDays).toFixed(3))
    }
  }
}
</script>
<style>
.progressbar{
  text-align: left;
  margin-top:10px;
  margin-bottom:40px;
  width:100%;}
progress{
    margin-bottom: 10px;
  }

.el-progress-bar__inner{
  -webkit-tap-highlight-color: transparent;
  height: 100%;
  border-radius: 5px;
  transition: width .35s;
  animation: progress 750ms linear infinite;
  background-size: 30px 30px;
}

</style>
