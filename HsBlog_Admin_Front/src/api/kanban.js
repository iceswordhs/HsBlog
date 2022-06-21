import axios from '@/utils/request'

export function getKanbanData(){
    return axios({
        url:'kanbanData',
        method:'get'
    })
}

export function saveKanbanData(boards){
    return axios({
        url:'kanbanData',
        method:'post',
        data:boards
    })
}
