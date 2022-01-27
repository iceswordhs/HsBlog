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
    url:'',
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
      queryInfo
    }
  })
}

export function updateTop(id,top){
  return axios({
    url:'updateBlogTop',
    method:'POST',
    params:{
      id,
      top
    }
  })
}

export function updatePublished(id,published){
  return axios({
    url:'updateBlogPublished',
    method:'POST',
    params:{
      id,
      published
    }
  })
}
