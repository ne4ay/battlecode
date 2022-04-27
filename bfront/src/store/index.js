import {createStore} from "vuex";

export default createStore({
    state: {
        isAuth: false,
        basicProfileInfo: {
            login: null,
            experience: null,
            level: null
        }
    },
    actions: {

    }
})