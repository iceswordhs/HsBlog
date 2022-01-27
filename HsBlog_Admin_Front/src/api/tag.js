import axios from '@/utils/request'

export function getAllTags () {
  return axios({
    url: '/getAllTags',
    method: 'GET'
  })
}

export function getData(queryInfo){
  return axios({
    url:'getTagByPage',
    method:'GET',
    params:queryInfo
  })
}

export function addTag(tag){
  return axios({
    url:'addTag',
    method:'POST',
    data:{
      ...tag
    }
  })
}

export function editTag(tag){
  return axios({
    url:'updateTag',
    method:'POST',
    data:{
      ...tag
    }
  })
}

export function deleteTagById(id){
  return axios({
    url:'/deleteTagById',
    method:'DELETE',
    params:{
      id
    }
  })
}
