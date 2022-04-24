import axios from "axios";

export default {
    data() {
        return {
            isAuthenticated: true
        }
    },
    methods: {

    },
    beforeCreate() {
        axios.get();
    }

}