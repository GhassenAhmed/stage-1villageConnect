import Axios from "axios";

import "@/plugins/axios"

export default {
    async getServiceRaiting(page,search,per){
        const res= await Axios.get(`getServiceRaiting?${search ? "search="+search : ""}&page=${page}&per_page=${per}`);
        return res;
    },
}