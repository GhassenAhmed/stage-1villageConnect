import Axios from "axios";

import "@/plugins/axios"

export default {

    getAllChatPrivate(id){
        return Axios.get("AllChatPrivate?idrecu="+id);
    },
    addMessagePrivate(id, message){
        return Axios.post("AddMessagePrivate?id="+id+"&message="+message);  
    },
    getAmis(){
        return Axios.get("getAmis"); 
    }


}