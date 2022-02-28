// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import store from '@/store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/icon/iconfont.css'
import SuiVue from 'semantic-ui-vue'
import 'semantic-ui-css/semantic.min.css'

import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// moment
import './util/dateTimeFormat.js'
// v-viewer
import 'viewerjs/dist/viewer.css'
import Viewer from 'v-viewer'
// typo.css
import './assets/css/typo.css'
import './assets/css/base.css'

// 防抖节流js
import './util/directive'

Vue.use(SuiVue)
Vue.use(Viewer)
Vue.use(ElementUI)

Vue.config.productionTip = false
// axios.defaults.baseURL = 'https://backend.smarths.cn'
axios.defaults.baseURL = 'http://localhost:10000'
Vue.prototype.$axios = axios

// 添加请求拦截器
axios.interceptors.request.use(
  config => {
    const identification = window.localStorage.getItem('identification')
    // identification存在，且是基于baseURL的请求
    if (identification && !(config.url.startsWith('http://') || config.url.startsWith('https://'))) {
      config.headers.identification = identification
    }
    return config
  }, function (error) {
    return Promise.reject(error)
  })

// 响应拦截
axios.interceptors.response.use(
  config => {
    const identification = config.headers.identification
    if (identification) {
      // 保存身份标识到localStorage
      window.localStorage.setItem('identification', identification)
    }
    return config
  }
)

Vue.use(ElementUI)
Vue.use(SuiVue)
Vue.use(mavonEditor)

Vue.prototype.successMsg = function (msg) {
  this.$message.success(msg)
}

Vue.prototype.errorMsg = function (msg) {
  this.$message.error(msg)
}

Vue.prototype.infoMsg = function (msg) {
  this.$message.info(msg)
}

const cubic = value => Math.pow(value, 3)
const easeInOutCubic = value => value < 0.5 ? cubic(value * 2) / 2 : 1 - cubic((1 - value) * 2) / 2
// 滚动至页面顶部，使用 Element-ui 回到顶部 组件中的算法
Vue.prototype.scrollToTop = function () {
  const el = document.documentElement
  const beginTime = Date.now()
  const beginValue = el.scrollTop
  const rAF = window.requestAnimationFrame || (func => setTimeout(func, 16))
  const frameFunc = () => {
    const progress = (Date.now() - beginTime) / 500
    if (progress < 1) {
      el.scrollTop = beginValue * (1 - easeInOutCubic(progress))
      rAF(frameFunc)
    } else {
      el.scrollTop = 0
    }
  }
  rAF(frameFunc)
}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
