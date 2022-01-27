<template>
<el-container>
  <!--页面主体-->

    <!--侧边栏-->
    <el-aside :width="isCollapse? '54px' : '190px'">

      <!--头部-->
      <el-header>
        <div class="title">
          <img src="@/assets/logo.png"
               alt=""
               height="20">
          <span v-if="!isCollapse" class="titleText">HsBlogAdmin</span>
        </div>
      </el-header>

      <!--菜单-->
      <el-menu background-color="#333744"
               text-color="#fff"
               active-text-color="#409eff"
               :default-openeds="defaultOpeneds"
               :unique-opened="false"
               :collapse="isCollapse"
               :collapse-transition="false"
               :router="true"
               :default-active="$store.state.activePath">
        <!-- 仪表盘 -->
        <el-menu-item index="/admin/dashboard">
          <i class="iconfont el-icon-odometer"></i>
          <span>仪表盘</span>
        </el-menu-item>
        <!-- 一级菜单 -->
        <el-submenu :index="item.id + ''"
                    v-for="item in menuList"
                    :key="item.id">
          <!-- 一级菜单的模板区域 -->
          <template slot="title">
            <i class="iconfont"
               :class="iconsObj[item.id]"></i>
            <span>{{ item.title }}</span>
          </template>
          <!-- 二级菜单 -->
          <el-menu-item :index="subItem.path"
                        v-for="subItem in item.children"
                        :key="subItem.id">
            <template slot="title">
              <i :class="iconsObj[subItem.id]"></i>
              <span>{{ subItem.title }}</span>
            </template>
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
<!--      <el-header-->
<!--        :class="isCollapse?'header-left':'header-left-width'">-->

<!--      </el-header>-->
      <!--右侧内容主体-->
      <el-main :class="isCollapse?'m-el-main-width-64':'m-el-main-width-190'">
        <!--主体上部-->
        <div class="main-header">
        <el-row type="flex" justify="true">
          <el-col :span="1">
            <!--收起与展开导航栏-->
            <div class="toggle-button"
                 @click="isCollapse=!isCollapse"><i :class="isCollapse?'el-icon-s-unfold':'el-icon-s-fold'"></i></div>
          </el-col>
          <!--面包屑导航-->
          <el-col :span="5">
            <Breadcrumb parentTitle="博客管理" />
          </el-col>
          <el-col style="text-align: right">
            <el-dropdown trigger="click"
                         class="user"
                         v-if="user"
                         @command="logout">
              <div class="el-dropdown-link">
                <div class="demo-basic--circle" style="margin-right: 15px">
                  <el-avatar shape="square"
                             size="medium"
                             fit="contain"
                             :src="user.avatarImgUrl"></el-avatar>
                </div>
              </div>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item icon="ali-iconfont icon-logout">退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>
        </el-row>
        </div>

        <!--加 key 让组件被重用时 重新执行生命周期 否则在编辑文章页面路由到写文章页面时 数据被重用-->
        <router-view :key="$route.fullPath" />
      </el-main>
    </el-container>
</el-container>
</template>

<script>
import Breadcrumb from '@/adminView/dashboard/Breadcrumb.vue'
export default {
  name: 'AdminHome',
  data () {
    return {
      menuList: [
        {
          id: 1,
          title: '博客管理',
          children: [
            {
              id: 11,
              title: '写文章',
              path: '/admin/editblog'
            },
            {
              id: 12,
              title: '写动态',
              path: '/moments/write'
            },
            {
              id: 13,
              title: '文章管理',
              path: '/blogs'
            },
            {
              id: 14,
              title: '动态管理',
              path: '/moments'
            },
            {
              id: 15,
              title: '分类管理',
              path: '/categories'
            },
            {
              id: 16,
              title: '标签管理',
              path: '/tags'
            },
            {
              id: 17,
              title: '评论管理',
              path: '/comments'
            }
          ]
        },
        {
          id: 2,
          title: '页面管理',
          children: [
            {
              id: 21,
              title: '站点设置',
              path: '/siteSettings'
            },
            {
              id: 22,
              title: '友链管理',
              path: '/friends'
            },
            {
              id: 23,
              title: '关于我',
              path: '/about'
            }
          ]
        },
        {
          id: 3,
          title: '系统管理',
          children: [
            {
              id: 31,
              title: '定时任务',
              path: '/jobs'
            }
          ]
        },
        {
          id: 4,
          title: '日志管理',
          children: [
            {
              id: 41,
              title: '任务日志',
              path: '/jobs/logs'
            },
            {
              id: 42,
              title: '登录日志',
              path: '/loginLog'
            },
            {
              id: 43,
              title: '操作日志',
              path: '/operationLog'
            },
            {
              id: 44,
              title: '异常日志',
              path: '/exceptionLog'
            },
            {
              id: 45,
              title: '访问日志',
              path: '/visitLog'
            }
          ]
        },
        {
          id: 5,
          title: '数据统计',
          children: [
            {
              id: 51,
              title: '访客统计',
              path: '/visitor'
            },
            {
              id: 52,
              title: '受访页面',
              path: '/visitPage'
            }
          ]
        }
      ],
      iconsObj: {
        '1': 'el-icon-menu',
        '2': 'el-icon-document-copy',
        '3': 'el-icon-s-tools',
        '4': 'el-icon-document',
        '5': 'el-icon-s-data',
        '11': 'el-icon-edit',
        '12': 'el-icon-edit',
        '13': 'el-icon-s-order',
        '14': 'el-icon-chat-dot-round',
        '15': 'el-icon-s-opportunity',
        '16': 'submenu ali-iconfont icon-biaoqian',
        '17': 'el-icon-s-comment',
        '21': 'submenu ali-iconfont icon-bianjizhandian',
        '22': 'submenu ali-iconfont icon-friend',
        '23': 'el-icon-tickets',
        '31': 'el-icon-alarm-clock',
        '41': 'el-icon-alarm-clock',
        '42': 'el-icon-finished',
        '43': 'el-icon-document-checked',
        '44': 'el-icon-document-delete',
        '45': 'el-icon-data-line',
        '51': 'el-icon-s-marketing',
        '52': 'el-icon-view'
      },
      // 是否折叠
      isCollapse: false,
      // 默认打开的菜单
      defaultOpeneds: ['1', '2', '3', '4', '5'],
      user: null
    }
  },
  components: {
    Breadcrumb
  },
  created () {
    this.getUserInfo()
  },
  methods: {
    getUserInfo () {
      this.user = JSON.parse(window.localStorage.getItem('user') || null)
      this.successMsg(this.user.avatarImgUrl)
      console.log(this.user.avatarImgUrl)
      if (!this.user) {
        this.$router.push('/login')
      }
    },
    logout () {
      window.localStorage.clear()
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
/*页面主体*/
.home-container {
  height: 100%;
}

.titleText{
  font-size: 15px;
}
.el-aside {
  -ms-overflow-style: none; /* IE10 */
  scrollbar-width: none; /* Firefox */
}
/* 去除左侧导航栏的滚动条 */
.el-aside::-webkit-scrollbar {
  display: none;
}

.el-aside {
  background-color: #333744;
  position: absolute;
  top: 0px;
  bottom: 0;
  user-select: none;
  text-align: left;
}

.el-aside::-webkit-scrollbar {
  display: none;
}

.el-aside .el-menu {
  border-right: none;
}

.el-submenu .el-menu-item {
  min-width: inherit;
}

/*head 部分*/

/*main-header部分*/
.main-header {
  /*设置为粘贴模式*/
  position: fixed;
  top: 0;
  right: 0;
  box-shadow: 0 1px 4px rgb(0 21 41);
  width: auto;
}

.el-header {
  /*background-color: rgba(201, 112, 112, 0.34);*/
  background-color: rgb(153, 154, 156);
  display: flex;
  justify-content: space-between;
  padding-left: 10px;
  align-items: center;
  color: #ffffff;
  font-size: 22px;
  user-select: none;
  line-height: 40px;
  text-align: right;
}

.header-left{
  width: calc(100% - 190px);
}
.header-left-width{
  width: calc(100% - 64px);
}

/*.el-header div {*/
/*  display: flex;*/
/*  align-items: center;*/
/*}*/

/*.el-header span {*/
/*  margin-left: 15px;*/
/*}*/

.el-dropdown-link {
  outline-style: none !important;
  outline-color: unset !important;
  height: 100%;
  cursor: pointer;
}

.el-dropdown-menu {
  margin: 7px 0 0 0 !important;
  padding: 0 !important;
  border: 0 !important;
}

/*main部分*/
.el-main {
  background-color: #ffffff;
  position: relative;
  top: 0px;
  bottom: 0;
  right: 0;
  /*如果溢出，提供滚动机制*/
  overflow-y: auto;
  overflow-x: hidden;
}

.m-el-main-width-190 {
  width: calc(100% - 190px);
}

.m-el-main-width-64 {
  width: calc(100% - 54px);
}
/* main部分的滚动条 */
.el-main::-webkit-scrollbar {
  width: 8px;
  height: 5px;
}
/*滚动条中的小方块*/
.el-main::-webkit-scrollbar-thumb {
  -webkit-box-shadow: inset 0 0 6px #3b20d1;
  box-shadow: inset 0 0 6px #ae20d1;
  background-color: #3b20d1;
  border-radius: 0px;
}
/*滚动条的轨道（里面装有Thumb）*/
.el-main::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px transparent;
  box-shadow: inset 0 0 6px transparent;
  background-color: transparent;
}
/*内层轨道，滚动条中间部分*/
.el-main::-webkit-scrollbar-track-piece {
  background-color: transparent;
}

.iconfont {
  margin-right: 20px;
  font-size: 20px;
}

.submenu.ali-iconfont {
  vertical-align: middle;
  margin-right: 5px;
  width: 24px;
  text-align: center;
  display: inline-block;
}

.toggle-button {
  background-color: #ffffff;
  font-size: 20px;
  color: #000000;
  text-align: center;
  cursor: pointer;
}
</style>
