import request from "../utils/request";

export function getVisitorList(queryInfo) {
    return request({
        url: 'visitors',
        method: 'GET',
        params: {
            ...queryInfo
        }
    })
}

export function deleteVisitor(id, uuid) {
    return request({
        url: 'visitor',
        method: 'DELETE',
        params: {
            id,
            uuid
        }
    })
}
