import axios from '@/utils/request'

export function getBlogById (id) {
  return axios({
    url: 'blog',
    method: 'GET',
    params: {
      id:id
    }
  })
}

export function getSearchBlogList (query) {
  return axios({
    url: 'searchBlog',
    method: 'GET',
    params: {
      query
    }
  })
}

export function saveBlog (blog) {
  return axios({
    url: 'saveBlog',
    method: 'POST',
    data: {
      ...blog
    }
  })
}

export function deleteBlogById(id){
  return axios({
    url:'deleteBlogById',
    method:'POST',
    params:{
      id
    }
  })
}

export function getDataByQuery(queryInfo){
  return axios({
    url:'manageBlog',
    method:'GET',
    params:{
      ...queryInfo
    }
  })
}

export function updateTop(id,top){
  return axios({
    url:'/blog/top',
    method:'POST',
    params:{
      id,
      top
    }
  })
}

export function updateRecommend(id, recommend) {
  return axios({
    url: 'blog/recommend',
    method: 'POST',
    params: {
      id,
      recommend
    }
  })
}

export function updatePublished(id,published){
  return axios({
    url:'/blog/published',
    method:'POST',
    params:{
      id,
      published
    }
  })
}
