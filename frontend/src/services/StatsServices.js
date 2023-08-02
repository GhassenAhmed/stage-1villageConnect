import Axios from "axios";

import "@/plugins/axios"

export default {

    getClient(){
        return Axios.get("getUserClient");
    },
    getServiceProvider(){
        return Axios.get("getUserServiceProvider");
    },
    getServiceVerified(){
        return Axios.get("getCountServiceVerified");
    },
    getServiceNonVerified(){
        return Axios.get("getCountServiceNonVerified");
    },
    getAllUsers(){
        return Axios.get("getAllUsers");
    },

    getServiceNotActived(){
        return Axios.get("getServiceNotActived");
    },

    updateService(id){
        return Axios.post("updateService?id="+id);
    },
    deleteService(id){
        return Axios.delete("deleteService?id="+id);
    },
    countUsers(){
        return Axios.get("countUsers");
    },

    countVillages(){
        return Axios.get("countVillages");
    },
    countServices(){
        return Axios.get("countServices");
    },
    countCategories(){
        return Axios.get("countCategories");
    },

    getAllCategorie(){
        return Axios.get("getAll");
    },

    deleteCategorie(id){
        return Axios.delete("deleteCategorie?id="+id);
    },

    getAllService(){
        return Axios.get("getAllService");
    },

    addCategorie(){
        return Axios.post("addCategorie");
    }
}