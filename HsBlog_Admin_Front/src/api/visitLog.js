import request from "../utils/request";

export function getVisitLogList(queryInfo) {
    return request({
        url: 'visitLogs',
        method: 'GET',
        params: {
            ...queryInfo
        }
    })
}

export function deleteVisitLogById(id) {
    return request({
        url: 'visitLog',
        method: 'DELETE',
        params: {
            id
        }
    })
}
