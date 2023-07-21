import Axios from "axios";

import "@/plugins/axios"

export default {
    
    getAllVillages(){
        return Axios.get("getAllVillages");
    }
}