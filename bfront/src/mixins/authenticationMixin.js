import axios from "axios";
import Properties from "@/Properties";

export default {
    methods: {
        async getBasicProfileInfo() {
            return await axios.get(Properties.BBACK_ADDRESS + "/profile/basic", {
                withCredentials: true,
            });
        },
    },

}