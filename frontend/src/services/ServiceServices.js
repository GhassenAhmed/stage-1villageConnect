import Axios from "axios";

import "@/plugins/axios"

export default {
    async getServiceRaiting(page,id,per){
        const res= await Axios.get(`getServiceRaiting?id=${id}&page=${page}&per_page=${per}`);
        return res;
    },
    async getServiceVerified(page,id,per){
        const res= await Axios.get(`getServiceVerified?id=${id}&page=${page}&per_page=${per}`);
        return res;
    },
    createService(service){
        return Axios.post("createService",service)
    },
   async  getServiceDetailsById(id){
        const res = await Axios.get("getServiceDetails?id="+id);
        return res;
    }
}