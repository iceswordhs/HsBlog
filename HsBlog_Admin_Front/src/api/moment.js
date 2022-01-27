import request from "@/utils/request";

export function saveMoment(moment){
    return request({
        url:'/createMoment',
        method:'POST',
        data:{
            ...moment
        }
    })
}

export function getMomentById(id){
    return request({
        url:'/getMomentById',
        method:'GET',
        params:{
            id:id
        }
    })
}

export function updateMoment(moment){
    return request({
        url:'/updateMoment',
        method:'POST',
        data:{
            ...moment
        }
    })
}

export function updatePublished(id,published){
    return request({
        url:'/updateMomentPublished',
        method:'POST',
        params:{
            id:id,
            publish:published
        }
    })
}

export function getMomentListByQuery(query){
    return request({
        url:'/manageMoments',
        method:'GET',
        params:{
            query
        }
    })
}

export function deleteMomentById(id){
    return request({
        url:'/deleteMoment',
        method:'POST',
        params:{
            id:id
        }
    })
}
