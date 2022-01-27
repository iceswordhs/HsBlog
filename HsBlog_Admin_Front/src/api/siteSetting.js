import request from "@/utils/request";

export function getSiteSettingData() {
    return request({
        url: 'siteSettings',
        method: 'GET'
    })
}

export function update(settings, deleteIds) {
    return request({
        url: 'siteSettings',
        method: 'POST',
        data: {
            settings,
            deleteIds
        }
    })
}

export function getWebTitleSuffix() {
    return request({
        url: 'webTitleSuffix',
        method: 'GET'
    })
}
