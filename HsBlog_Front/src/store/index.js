import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import actions from './actions'
import mutations from './mutations'
// import getters from './getters'

Vue.use(Vuex)

// 想要改变Vuex状态就需要显示的提交mutations，这就是Vuex和全局变量的区别
// 调用的时候xxx.store.commit('mutations的名字')

export default new Vuex.Store({
  state,
  actions,
  mutations,
  modules: {}
})
