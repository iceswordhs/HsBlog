import request from "../utils/request";

export function getOperationLogList(queryInfo) {
    return request({
        url: 'operationLogs',
        method: 'GET',
        params: {
            ...queryInfo
        }
    })
}

export function deleteOperationLogById(id) {
    return request({
        url: 'operationLog',
        method: 'DELETE',
        params: {
            id
        }
    })
}
