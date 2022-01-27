import axios from '@/utils/request'

export function uploadImage(formData){
    return axios({
        method:'POST',
        url:'/uploadImage',
        data:formData,
        header:{'Content-Type':'multipart/form-data'}
    })
}
