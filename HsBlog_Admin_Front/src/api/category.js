import axios from '@/utils/request'

export function getAllCategories () {
  return axios({
    url: '/getAllCategories',
    method: 'GET'
  })
}

export function getData (queryInfo) {
  return axios({
    url: '/getCategoriesByPage',
    method: 'GET',
    params:{
      queryInfo
    }
  })
}

export function addCategory(category){
  return axios({
    url:'/addCategory',
    method:'POST',
    data:{
      ...category
    }
  })
}

export function deleteCategoryById(id){
  return axios({
    url:'/deleteCategory',
    method:'DELETE',
    params:{
      id
    }
  })
}

export function editCategory(editForm){
  return axios({
    url:'/updateCategory',
    method:'POST',
    data:{
      ...editForm
    }
  })
}


