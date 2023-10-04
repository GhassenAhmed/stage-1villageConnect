import Axios from "axios";

import "@/plugins/axios"

export default {

    TestEmail(email){
        return Axios.get("ExistMail?email="+email);
    },
    getUserAuthentifie(){
        return Axios.get("getUserAuthentifie");
    },
    TestExistcode(code){
        return Axios.get("ExistToken?code="+code);
    },
    UpdateStatus(info){
        return Axios.post("UpdateStatus",info);
    },
    UpdateStatusRole(id){
        return Axios.post("updateStatus?id="+id);
    },
    deleteUser(id){
        return Axios.delete("deleteUser?id="+id);
    }

}