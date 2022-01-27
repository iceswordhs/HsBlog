import axios from 'axios'

export function getAllTags () {
  return axios({
    url: '/getAllTags',
    method: 'GET'
  })
}

export function getBlogByTag (tagName, pageNum) {
  return axios({
    url: '/tag',
    method: 'GET',
    params: {
      tagName: tagName,
      pageNum: pageNum
    }
  })
}
