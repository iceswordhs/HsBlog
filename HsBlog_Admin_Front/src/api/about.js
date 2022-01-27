import request from "@/utils/request";

export function getAbout(){
    return request({
        url:'/about',
        method:'GET'
    })
}

export function updateAbout(about){
    return request({
        url:'/updateAbout',
        method:'POST',
        data:{
            ...about
        }
    })
}
