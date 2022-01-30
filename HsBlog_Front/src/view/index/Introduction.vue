<template>
  <div>
    <div class="ui segments m-box">
      <div class="ui card">
        <div class="image">
          <img :src="introduction.avatar.value">
        </div>
        <div class="content" align="center">
          <div class="header">{{ introduction.name }}</div>
          <!--彩色滚动字体-->
          <div id="rollText" class="m-margin-top" style="font-size: 15px;" v-if="introduction.rollText.length!==0"></div>
        </div>
        <div>
          <el-collapse accordion>
            <el-collapse-item :title="item.title" :name="index" v-if="item.title" v-for="(item,index) in introduction.customizes" :key="index">
              <div>{{ item.content }}</div>
            </el-collapse-item>
          </el-collapse>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'
export default {
  name: 'Introduction',
  computed: {
    ...mapState(['introduction'])
  },
  watch: {
    'introduction.rollText' () {
      if (this.introduction.rollText.length !== 0) {
        // 等待 id="rollText"的div加载完毕
        this.$nextTick(() => {
          this.rollText()
        })
      }
    }
  },
  methods: {
    rollText () {
      let r = document.getElementById('rollText')
      let l = ''
      let o = this.introduction.rollText.map(function (r) {
        return r + ''
      })
      let a = 2
      let g = 1
      let s = 5
      let d = 75
      let b = ['rgb(110,64,170)', 'rgb(150,61,179)', 'rgb(191,60,175)', 'rgb(228,65,157)', 'rgb(254,75,131)', 'rgb(255,94,99)', 'rgb(255,120,71)', 'rgb(251,150,51)', 'rgb(226,183,47)', 'rgb(198,214,60)', 'rgb(175,240,91)', 'rgb(127,246,88)', 'rgb(82,246,103)', 'rgb(48,239,130)', 'rgb(29,223,163)', 'rgb(26,199,194)', 'rgb(35,171,216)', 'rgb(54,140,225)', 'rgb(76,110,219)', 'rgb(96,84,200)']
      let c = {text: '', prefixP: -s, skillI: 0, skillP: 0, direction: 'forward', delay: a, step: g}
      function t () {
        return b[Math.floor(Math.random() * b.length)]
      }
      function e () {
        return String.fromCharCode(94 * Math.random() + 33)
      }
      function n (r) {
        for (var n = document.createDocumentFragment(), i = 0; r > i; i++) {
          let l = document.createElement('span')
          // eslint-disable-next-line no-unused-expressions,no-sequences
          l.textContent = e(), l.style.color = t(), n.appendChild(l)
        }
        return n
      }
      function i () {
        let t = o[c.skillI]
        // eslint-disable-next-line no-unused-expressions,no-sequences
        c.step ? c.step-- : (c.step = g, c.prefixP < l.length ? (c.prefixP >= 0 && (c.text += l[c.prefixP]), c.prefixP++) : c.direction === 'forward' ? c.skillP < t.length ? (c.text += t[c.skillP], c.skillP++) : c.delay ? c.delay-- : (c.direction = 'backward', c.delay = a) : c.skillP > 0 ? (c.text = c.text.slice(0, -1), c.skillP--) : (c.skillI = (c.skillI + 1) % o.length, c.direction = 'forward')), r.textContent = c.text, r.appendChild(n(c.prefixP < l.length ? Math.min(s, s + c.prefixP) : Math.min(s, t.length - c.skillP))), setTimeout(i, d)
      }
      i()
    }
  }
}
</script>

<style>
.el-collapse-item {
  padding-left: 15px;
  padding-right: 15px;
}
.el-collapse-item .el-collapse-item__content {
  padding-bottom: 10px;
}
</style>
