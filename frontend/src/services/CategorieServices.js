import Axios from "axios";

import "@/plugins/axios"

export default {

    async getCategories(page,per){
        const res = await Axios.get(`getAllCategorie?page=${page}&per_page=${per}`);
        return res;
    }
}