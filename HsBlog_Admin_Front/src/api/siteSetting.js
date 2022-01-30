import axios from '@/utils/request'

export function getSiteSettingData() {
    return axios({
        url: 'site',
        method: 'GET'
    })
}

export function update(settings, deleteIds) {
    return axios({
        url: 'siteSettings',
        method: 'POST',
        data: {
            settings,
            deleteIds
        }
    })
}

export function getWebTitleSuffix() {
    return axios({
        url: 'webTitleSuffix',
        method: 'GET'
    })
}
