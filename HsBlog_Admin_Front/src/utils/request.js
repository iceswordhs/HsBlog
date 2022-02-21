import axios from 'axios'
import {Message} from 'element-ui'
// 导入NProgress进度条
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

// 创建一个实例
const service = axios.create({
  baseURL: 'https://backend.smarths.cn/admin',
  // baseURL: 'http://localhost:10000/admin',
  timeout: 5000 // request timeout
})

let CancelToken = axios.CancelToken

// 请求拦截
service.interceptors.request.use(
  config => {
      //对于访客模式，除GET请求外，都拦截并提示
      const userJson = window.localStorage.getItem('user') || '{}'
      const user = JSON.parse(userJson)
      if (userJson !== '{}' && !user.role.includes('ROLE_admin') && config.method !== 'get') {
          config.cancelToken = new CancelToken(function executor(cancel) {
              cancel('演示模式，不允许操作')
          })
          return config
      }

    NProgress.start()
    const token = window.localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = 'Bearer '+ token
    }
    return config
  },
  error => {
    // do something with request error
    console.log('请求错误！') // for debug
    return Promise.reject(error)
  }
)

// 响应拦截
service.interceptors.response.use(
  response => {
    NProgress.done()
    const res = response.data
    if (res.code !== 200) {
      let msg = res.message || 'Error'
      Message.error(msg)
      return Promise.reject(new Error(msg))
    }
    return res
  },
    error => {
      console.info(error)
      Message.error(error.message)
      return Promise.reject(error)
    }
)

export default service
