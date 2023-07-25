import Axios from "axios";

import "@/plugins/axios"

export default {
    async getServiceRaiting(page,id,per){
        const res= await Axios.get(`getServiceRaiting?id=${id}&page=${page}&per_page=${per}`);
        return res;
    },
}