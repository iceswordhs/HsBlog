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

export function getSentences() {
    return axios({
        url: 'sentences',
        method: 'GET'
    })
}

export function updateSentence(sentences, deleteIds) {
    return axios({
        url: 'sentences',
        method: 'POST',
        data: {
            sentences,
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
