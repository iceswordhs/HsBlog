import Vue from 'vue'
import Router from 'vue-router'

import Home from '@/view/index/Home'
import Index from '@/view/index/Index'
import Comment from '@/view/comment/Comment'

// import Blog from '../view/blog/Blog'

import Login from '@/adminView/Login'
import AdminHome from '@/adminView/AdminHome'
import Dashboard from '@/adminView/dashboard/Dashboard'
import EditBlog from '@/adminView/blog/EditBlog'

// import {SAVE_NAV_STATE} from '@/store/mutations-types'
import store from '@/store'
import MyBlogItem from '../view/myblog/MyBlogItem'

Vue.use(Router)

const routes = [
  {
    path: '/comment',
    name: 'Comment',
    component: Comment
  },
  {
    path: '/admin/login',
    name: 'Login',
    component: Login,
    meta: {
      title: '登录'
    }
  },
  {
    path: '/',
    name: 'Index',
    component: Index,
    redirect: '/home',
    children: [
      {
        path: '/home',
        name: 'Home',
        component: Home,
        meta: {
          title: '首页'
        }
      },
      {
        path: '/blog/:blogId',
        name: 'Blog',
        component: () => import('@/view/blog/Blog'),
        meta: {
          title: '博客'
        }
      },
      {
        path: '/archives',
        name: 'Archives',
        component: () => import('@/view/archives/Archives'),
        meta: {
          title: '归档'
        }
      },
      {
        path: '/friends',
        name: 'Friends',
        component: () => import('@/view/friend/Friends'),
        meta: {
          title: '友链'
        }
      },
      {
        path: '/about',
        name: 'About',
        component: () => import('@/view/about/About'),
        meta: {
          title: '关于我'
        }
      },
      {
        path: '/category/:name',
        name: 'Category',
        component: () => import('@/view/category/Category'),
        meta: {
          title: '分类'
        }
      },
      {
        path: '/tag/:name',
        name: 'Tag',
        component: () => import('@/view/tag/Tag'),
        meta: {
          title: '标签'
        }
      },
      {
        path: '/moments',
        name: 'Moments',
        component: () => import('@/view/moment/Moments'),
        meta: {
          title: '动态'
        }
      },
      {
        path: '/testBlog',
        name: 'TestBlog',
        component: MyBlogItem,
        meta: {
          title: '动态'
        }
      }
    ]
  },
  {
    path: '/admin',
    name: '后台管理',
    component: AdminHome,
    redirect: '/admin/dashboard',
    children: [
      {
        path: '/admin/dashboard',
        name: '仪表盘',
        meta: {
          title: '仪表盘'
        },
        component: Dashboard
      },
      {
        path: '/admin/editblog',
        name: 'editblog',
        meta: {
          title: '写博客'
        },
        component: EditBlog
      }
    ]
  }
]

const router = new Router({
  mode: 'history',
  // 单页应用的基路径
  // base: process.env.BASE_URL,
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  // // 如果没有登录，自动跳转到登录页面
  // if (to.path !== '/admin/login') {
  //   // 获取token
  //   const tokenStr = window.localStorage.getItem('token')
  //   if (!tokenStr) return next('/admin/login')
  // }

  // 如果要跳转的页面有标题
  if (to.meta.title) {
    // 如果网站标题前缀存在
    if (store.state.siteInfo.webTitleSuffix) {
      document.title = to.meta.title + store.state.siteInfo.webTitleSuffix
    } else {
      document.title = to.meta.title
    }
  }
  // 调用SAVE_NAV_STATE，让Vuex的activePath变为要跳转页面的路径
  // router.app.$options.store.commit(SAVE_NAV_STATE, to.path)
  next()
})
export default router
