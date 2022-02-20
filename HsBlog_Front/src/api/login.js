import axios from 'axios'

export function login (loginForm) {
  return axios({
    url: 'login',
    method: 'POST',
    data: {
      ...loginForm
    }
  })
}
