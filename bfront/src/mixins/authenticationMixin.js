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
            localStorage.setItem('level', responseModel.level);
            localStorage.setItem('isAuth', true);
        },
        resetProfileInfo() {
            localStorage.removeItem('login');
            localStorage.removeItem('experience');
            localStorage.removeItem('level');
            localStorage.setItem('isAuth', false);
        },
        getProfileInfo() {
            return {
                login: localStorage.getItem('login'),
                experience: localStorage.getItem('experience'),
                level: localStorage.getItem('level'),
            }
        }
    },

}