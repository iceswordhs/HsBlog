import request from "../utils/request";

export function getExceptionLogList(queryInfo) {
    return request({
        url: 'exceptionLogs',
        method: 'GET',
        params: {
            ...queryInfo
        }
    })
}

export function deleteExceptionLogById(id) {
    return request({
        url: 'exceptionLog',
        method: 'DELETE',
        params: {
            id
        }
    })
}
