import request from "../utils/request";

export function getCommentListByQuery(queryInfo) {
    return request({
        url: 'comments',
        method: 'GET',
        params: {
            ...queryInfo
        }
    })
}

export function getBlogList() {
    return request({
        url: 'blogIdAndTitle',
        method: 'GET'
    })
}

export function updatePublished(id, published) {
    return request({
        url: 'comment/published',
        method: 'PUT',
        params: {
            id,
            published
        }
    })
}

export function updateNotice(id, notice) {
    return request({
        url: 'comment/notice',
        method: 'PUT',
        params: {
            id,
            notice
        }
    })
}

export function deleteCommentById(id) {
    return request({
        url: 'comment',
        method: 'DELETE',
        params: {
            id
        }
    })
}

export function editComment(form) {
    return request({
        url: 'comment',
        method: 'PUT',
        data: {
            ...form
        }
    })
}
