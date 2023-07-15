import { defineStore } from 'pinia'
import { ref,computed } from "vue"

export const AuthUser = defineStore('auth', ()=> {

    const token=ref(localStorage.getItem('token')??null);
    const user=ref(JSON.parse(localStorage.getItem('user')??null));
    const isAuth=ref(localStorage.getItem('token')&&localStorage.getItem('user'));
    const IsAdmin=ref(localStorage.getItem('IsAdmin')??false);
    const IsClient=ref(localStorage.getItem('IsClient')??false);
    const IsServiceProvider=ref(localStorage.getItem('IsServiceProvider')??false);

    const getUser=computed(()=>user.value);
    const gettoken=computed(()=>token.value);
    const getisauth=computed(()=>isAuth.value);
    const getIsadmin=computed(()=>IsAdmin.value);
    const getIsClient=computed(()=>IsClient.value);
    const getIsServiceProvider=computed(()=>IsServiceProvider.value);




})