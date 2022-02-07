import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '@/layout'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import getPageTitle from '@/utils/get-page-title'

Vue.use(VueRouter)

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
const routes = [
  {
    path: '/login',
    component: () => import('@/views/login/Login'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },

  {
    path: '/blog',
    name: 'Blog',
    redirect: '/blog/write',
    component: Layout,
    meta: {title: '博客管理', icon: 'el-icon-menu'},
    children: [
      {
        path: 'write',
        name: 'WriteBlog',
        component: () => import('@/views/blog/blog/EditBlog'),
        meta: {title: '写文章', icon: 'el-icon-edit'}
      },
      {
        path: 'edit/:id',
        name: 'EditBlog',
        component: () => import('@/views/blog/blog/EditBlog'),
        // meta: {title: '写文章', icon: 'el-icon-edit'}
      },
      {
        path: 'writeMoments/:id',
        name: 'WriteMoment',
        component: () => import('@/views/blog/moment/WriteMoment'),
        meta: {title: '写动态',icon: 'el-icon-edit'}
      },
      {
        path: 'blogList',
        name: 'BlogList',
        component: () => import('@/views/blog/blog/BLogList'),
        meta: {title: '文章管理',icon: 'el-icon-edit'}
      },
      {
        path: 'momentList',
        name: 'MomentList',
        component: () => import('@/views/blog/moment/MomentList'),
        meta: {title: '动态管理',icon: 'el-icon-edit'}
      },
      {
        path: 'categoryList',
        name: 'CategoryList',
        component: () => import('@/views/blog/category/CategoryList'),
        meta: {title: '分类管理',icon: 'el-icon-edit'}
      },
      {
        path: 'tagList',
        name: 'TagList',
        component: () => import('@/views/blog/tag/TagList'),
        meta: {title: '标签管理',icon: 'el-icon-edit'}
      }
    ]
  },


  {
    path: '/components',
    name: 'Components',
    redirect:'/components/kanban',
    component: Layout,
    meta: {title: '组件', icon: 'el-icon-s-help'},
    children: [
      {
        path: 'kanban',
        name: 'dialogKanban',
        component: () => import('@/views/components/drag-kanban'),
        meta: {title: '计划板', icon: 'el-icon-date'}
      },
      {
        path: 'kanban2',
        name: 'dialogKanban',
        component: () => import('@/views/components/drag-kanban'),
        meta: {title: '计划板2', icon: 'el-icon-date'}
      }
    ]
  },

  {
    path: '/imageManage',
    name: 'imageManage',
    redirect:'/imageManage/manage',
    component: Layout,
    meta: {title: '图片管理', icon: 'el-icon-picture'},
    children: [
      {
        path: 'manage',
        name: 'Manage',
        component: () => import('@/views/imageManage/Manage'),
        meta: {title: '图片管理', icon: 'el-icon-folder-opened'}
      },
      {
        path: 'setting',
        name: 'Setting',
        component: () => import('@/views/imageManage/Setting'),
        meta: {title: '设置', icon: 'el-icon-setting'}
      }
    ]
  },

  {
    path: '/siteManage',
    name:'siteManage',
    redirect: '/siteManage/about',
    component:Layout,
    meta:{title: '站点管理',icon: 'el-icon-picture'},
    children: [
      {
        path: '/about',
        name:'About',
        component:() => import('@/views/siteManage/About'),
        meta: {title: '关于我',icon: 'el-icon-picture'}
      },
      {
        path: '/friend',
        name:'Friend',
        component:() => import('@/views/siteManage/FriendList'),
        meta: {title: '友链管理',icon: 'el-icon-picture'}
      },
      {
        path: '/siteSetting',
        name:'SiteSetting',
        component:() => import('@/views/siteManage/SiteSetting'),
        meta: {title: '页面设置',icon: 'el-icon-picture'}
      }
    ]
  },

  {
    path: '/system',
    name:'System',
    redirect: '/system/scheduleList',
    component:Layout,
    meta:{title: '系统设置',icon: 'el-icon-picture'},
    children: [
      {
        path: '/scheduleList',
        name:'ScheduleList',
        component:() => import('@/views/system/ScheduleJobList'),
        meta: {title: '定时任务',icon: 'el-icon-picture'}
      }
    ]
  },

  {
    path: '/log',
    name:'Log',
    component:Layout,
    meta:{title: '日志',icon: 'el-icon-picture'},
    children: [
      {
        path: '/exceptionLog',
        name:'ExceptionLog',
        component:() => import('@/views/log/ExceptionLog'),
        meta: {title: '异常日志',icon: 'el-icon-picture'}
      }
    ]
  },

  {
    path: '/example',
    component: Layout,
    redirect: '/example/table',
    name: 'Example',
    meta: { title: 'Example', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/table/index'),
        meta: { title: 'Table', icon: 'table' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: 'Tree', icon: 'tree' }
      }
    ]
  },

  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: 'Form', icon: 'form' }
      }
    ]
  },

  {
    path: '/nested',
    component: Layout,
    redirect: '/nested/menu1',
    name: 'Nested',
    meta: {
      title: 'Nested',
      icon: 'nested'
    },
    children: [
      {
        path: 'menu1',
        component: () => import('@/views/nested/menu1/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: 'Menu1' },
        children: [
          {
            path: 'menu1-1',
            component: () => import('@/views/nested/menu1/menu1-1'),
            name: 'Menu1-1',
            meta: { title: 'Menu1-1' }
          },
          {
            path: 'menu1-2',
            component: () => import('@/views/nested/menu1/menu1-2'),
            name: 'Menu1-2',
            meta: { title: 'Menu1-2' },
            children: [
              {
                path: 'menu1-2-1',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
                name: 'Menu1-2-1',
                meta: { title: 'Menu1-2-1' }
              },
              {
                path: 'menu1-2-2',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
                name: 'Menu1-2-2',
                meta: { title: 'Menu1-2-2' }
              }
            ]
          },
          {
            path: 'menu1-3',
            component: () => import('@/views/nested/menu1/menu1-3'),
            name: 'Menu1-3',
            meta: { title: 'Menu1-3' }
          }
        ]
      },
      {
        path: 'menu2',
        component: () => import('@/views/nested/menu2/index'),
        name: 'Menu2',
        meta: { title: 'menu2' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由守卫，如果没有token就自动跳转到登录页
router.beforeEach(async (to, from, next) => {
  NProgress.start()
  if (to.path !== '/login') {
    //获取token
    const tokenStr = window.localStorage.getItem('token')
    if (!tokenStr) return next("/login")
  }
  document.title = getPageTitle(to.meta.title)
  next()
})

router.afterEach(() => {
  NProgress.done()
})


export default router
