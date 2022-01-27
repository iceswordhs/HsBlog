import axios from 'axios'

export function getArchives () {
  return axios({
    url: 'archive',
    method: 'GET'
  })
}
