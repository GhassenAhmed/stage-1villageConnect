import Axios from "axios";
import { AuthUser } from '@/store/AuthStore.js';
import "@/plugins/axios"

export default {

    StoreUser(user) {
        return Axios.post("signUp", user);
    },

}

