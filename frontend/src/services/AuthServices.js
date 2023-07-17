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
        await Axios.post("logIn", { email, password });
    },


}

