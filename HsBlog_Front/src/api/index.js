import axios from 'axios'

export function getSite () {
  return axios({
    url: 'site',
    method: 'GET'
  })
}
