import axios from '@/utils/request'

export function login(username,password){
    return axios({
        url: '/login',
        method: 'post',
        data:{
            username: username,
            password: password
        }
    })
}
