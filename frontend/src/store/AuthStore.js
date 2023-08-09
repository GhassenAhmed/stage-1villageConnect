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

    function logIn(t,u,IsAdm,IsCli,IsServ){
        token.value=t;
        user.value=u;
        isAuth.value=true;
        IsAdmin.value=IsAdm;
        IsClient.value=IsCli;
        IsServiceProvider.value=IsServ;

        localStorage.setItem('token',t);
        localStorage.setItem('IsAdmin',IsAdm);
        localStorage.setItem('IsClient',IsCli);
        localStorage.setItem('IsServiceProvider',IsServ);
        localStorage.setItem('user',JSON.stringify(u));
    }

    function logOut(){
        token.value=null;
        user.value=null;
        isAuth.value=false;
        IsAdmin.value=false;
        IsClient.value=false;
        IsServiceProvider.value=false;

        localStorage.removeItem('token');
        localStorage.removeItem('IsAdmin');
        localStorage.removeItem('IsClient');
        localStorage.removeItem('IsServiceProvider');
        localStorage.removeItem('user');
    }

    function SetUser(u){
        user.value=null;
        localStorage.removeItem('user');
        user.value=u;
        localStorage.setItem('user',JSON.stringify(u));
    }


    return {
        token,user,isAuth,IsAdmin,IsClient,IsServiceProvider,
        getUser,gettoken,getisauth,getIsadmin,getIsClient,getIsServiceProvider,
        logIn,logOut,SetUser
    }
})