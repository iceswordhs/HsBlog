import axios from '@/utils/request'

export function getSignStatusData(firstDay){
    return axios({
        url:'/signData',
        method:'GET',
        params:{
            firstDay
        }
    })
}

export function sign(today,type){
    return axios({
        url:'/sign',
        method:'POST',
        params:{
            today,type
        }
    })
}
