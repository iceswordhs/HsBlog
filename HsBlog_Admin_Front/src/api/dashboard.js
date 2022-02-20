import request from "../utils/request";

export function getDashboard() {
    return request({
        url: 'dashboard',
        method: 'GET'
    })
}
