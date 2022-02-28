import axios from 'axios'

export function getSite () {
  return axios({
    url: 'site',
    method: 'GET'
  })
}

export function getSentence () {
  return axios({
    url: 'sentence',
    method: 'GET'
  })
}
