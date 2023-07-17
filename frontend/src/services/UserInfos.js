import Axios from "axios";

import "@/plugins/axios"

export default {

    TestEmail(email){
        return Axios.get("ExistMail?email="+email);
    },

}