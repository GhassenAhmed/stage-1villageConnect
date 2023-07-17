import Axios from "axios";
import { AuthUser } from '@/store/AuthStore.js';
import "@/plugins/axios"

export default {

    StoreUser(user) {
        return Axios.post("signUp", user);
    },
    VerifyEmail(email) {
        return Axios.post("verify?email=" + email);
    },
    async login(email, password) {
        const store=AuthUser();
        const response=await Axios.post("logIn", { email, password });
        if (response.status==200){
            const roles=response.data.user['roles'];
            var client=false;
            var admin=false;
            var serviceProvider=false;

            for(let i=0;i<roles.length;i++){
                if(roles[i].roleName=="client"){
                    client=true;
                }
                if(roles[i].roleName=="admin"){
                    admin=true;
                }
                if(roles[i].roleName=="serviceProvider"){
                    serviceProvider=true;
                }
            }
            store.logIn(response.data.token,response.data.user,admin,client,serviceProvider)
        }else{
            store.logOut();
        }
    },


}

