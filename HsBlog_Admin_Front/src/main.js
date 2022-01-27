import Vue from 'vue'
// 语义化的CSS
import 'normalize.css/normalize.css'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

// 导入全局css
import '@/styles/index.scss'

// 导入mavonEditor
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

// 导入dateUtil
import './utils/dateTimeFormatUtils.js'

import App from './App'
import store from './store'
import router from './router'

import '@/icons' // icon

Vue.use(ElementUI)
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

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
