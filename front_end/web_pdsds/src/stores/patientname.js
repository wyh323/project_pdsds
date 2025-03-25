import {defineStore} from 'pinia'
import {ref} from 'vue'
export const usepatientNameStore = defineStore('patientName',()=>{

    const name = ref('')

    const setName = (newName)=>{
        name.value = newName
    }

    const removeName = ()=>{
        name.value = {}
    }

    return {name,setName,removeName}

},{persist:true})

export default usepatientNameStore;