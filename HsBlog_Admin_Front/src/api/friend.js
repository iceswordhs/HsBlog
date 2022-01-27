import request from "@/utils/request";

export function getFriendsByQuery(pageInfo){
    return request({
        url:'/friendItems',
        method:'GET',
        params:pageInfo
    })
}

export function updatePublished(id, published) {
    return request({
        url: 'friendItem/published',
        method: 'POST',
        params: {
            id,
            published
        }
    })
}

export function saveFriendItem(form) {
    return request({
        url: 'friendItem',
        method: 'POST',
        data: {
            ...form
        }
    })
}

export function updateFriendItem(form) {
    return request({
        url: 'friendItem',
        method: 'PUT',
        data: {
            ...form
        }
    })
}

export function deleteFriendItemById(id) {
    return request({
        url: 'friendItem',
        method: 'DELETE',
        params: {
            id
        }
    })
}

export function getFriendInfo() {
    return request({
        url: 'friendInfo',
        method: 'GET'
    })
}

export function updateCommentEnabled(commentEnable) {
    return request({
        url: 'friendInfo/commentEnable',
        method: 'PUT',
        params: {
            commentEnable
        }
    })
}

export function updateContent(content) {
    return request({
        url: 'friendInfo/content',
        method: 'PUT',
        params: {
            content
        }
    })
}
