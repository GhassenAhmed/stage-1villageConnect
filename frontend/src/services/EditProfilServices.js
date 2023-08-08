import Axios from "axios";
import "@/plugins/axios"

export default {
    uploadPhoto(data){
        return Axios.post("uploadPhoto",data);
    },
    EditInfoPersonnel(data){
        return Axios.post("EditInfoPersonnel",data);
    },

}