import {createStore} from "vuex";

export default createStore({
    state: {
        auth: {
            isAuth: false,
            xsrfToken: '',
            jSessionId: '',
        },
        basicProfileInfo: {
            login: null,
            experience: null,
            level: null
        }
    },
    actions: {

    }
})