import axios from 'axios'

export function getAllCategories () {
  return axios({
    url: '/getAllCategories',
    method: 'GET'
  })
}

export function getBlogByCategoryId () {
  return axios({
    url: '/category',
    method: 'GET'
  })
}

export function getBlogByCategoryName (categoryName, pageNum) {
  return axios({
    url: '/category',
    method: 'GET',
    params: {
      categoryName: categoryName,
      pageNum: pageNum
    }
  })
}
