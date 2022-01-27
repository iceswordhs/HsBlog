<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="$route.path">{{ parentTitle }}</el-breadcrumb-item>
      <el-breadcrumb-item>{{ $route.meta.title }}</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
export default {
  name: 'Breadcrumb',
  props: {
    parentTitle: {
      type: String,
      required: true
    }
  }
}
</script>

<style scoped>
</style>
