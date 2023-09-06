import Axios from "axios";

import "@/plugins/axios"

export default {

    async getCategories(page,per){
        const res = await Axios.get(`getAllCategorie?page=${page}&per_page=${per}`);
        return res;
    },

    getAll(){
        return Axios.get("getAll");
    },
    async getServiceCategorie(id){
        const res = await Axios.get("getServiceCategorie?id="+id);
        return res;
    },
    addCategorie(name){
        return Axios.post("addCategorie?name="+name);
    }
}