import axios from 'axios'

export function getMomentListByPageNum (token, pageNum) {
  return axios({
    url: 'moment',
    method: 'GET',
    headers: {
      Authorization: token
    },
    params: {
      pageNum: pageNum
    }
  })
}

export function likeMoment (id) {
  return axios({
    url: '/updateMomentLikes',
    method: 'GET',
    params: {
      id: id
    }
  })
}
