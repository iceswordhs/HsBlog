import axios from 'axios'
import {Message} from 'element-ui'
// 导入NProgress进度条
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

// 创建一个实例
const service = axios.create({
  //baseURL: 'https://backend.smarths.cn/admin',
  baseURL: 'http://localhost:10000/admin',
  timeout: 5000 // request timeout
})

// 请求拦截
service.interceptors.request.use(
  config => {
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
