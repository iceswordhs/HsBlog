import axios from 'axios'

export function getAbout () {
  return axios({
    url: 'about',
    method: 'GET'
  })
}
