import axios from 'axios'

export function getBlogById (token, id) {
  return axios({
    url: 'getBlogById',
    method: 'GET',
    headers: {
      Authorization: token
    },
    params: {
      id
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

export function getBlogList (pageNum) {
  return axios({
    url: 'getPageBlog',
    method: 'GET',
    params: {
      pageNum
    }
  })
}
