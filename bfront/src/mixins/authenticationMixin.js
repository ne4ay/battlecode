import axios from "axios";
import Properties from "@/Properties";

export default {
    methods: {
        async getBasicProfileInfo() {
            return await axios.get(Properties.BBACK_ADDRESS + "/profile/basic", {
                withCredentials: true,
            });
        },
        getAuth() {
            return localStorage.getItem('isAuth') === 'true';
        },
        updateProfileInfo(response) {
            const responseModel = response.data.response;
            localStorage.setItem('login', responseModel.login);
            localStorage.setItem('experience', responseModel.experience);
            localStorage.setItem('percentValueToNextLevel', responseModel.percentValueToNextLevel);
            localStorage.setItem('level', responseModel.level);
            localStorage.setItem('roles', JSON.stringify(responseModel.roles));
            localStorage.setItem('isAuth', true);
        },
        resetProfileInfo() {
            localStorage.removeItem('login');
            localStorage.removeItem('experience');
            localStorage.removeItem('level');
            localStorage.removeItem('percentValueToNextLevel');
            localStorage.removeItem('roles');
            localStorage.setItem('isAuth', false);
        },
        getProfileInfo() {
            return {
                login: localStorage.getItem('login'),
                experience: localStorage.getItem('experience'),
                percentValueToNextLevel: localStorage.getItem('percentValueToNextLevel'),
                level: localStorage.getItem('level'),
                roles: JSON.parse(localStorage.getItem('roles')),
            }
        }
    },

}