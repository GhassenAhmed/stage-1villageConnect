import { defineStore } from 'pinia'
import { ref,computed } from "vue"

export const CurentView = defineStore('current', ()=> {
  const view=ref(localStorage.getItem('view')??'stat');
  
  const getView=computed(()=>view.value);
  return{view,getView,setView,suppView}
})
