import authenticationMixin from "@/mixins/authenticationMixin";
import router from "@/router/router";

export default {
    methods: {
        handleException(exception) {
            if (exception.response.status === 401 || exception.response.status === 403) {
                authenticationMixin.methods.resetProfileInfo();
                router.push('/auth');
                return;
            }
            router.push('/error?error=' + exception);
        },
        determineId(path) {
            const splitPath = path.split('/');
            return parseInt(splitPath[splitPath.length - 1]);
        }
    }
}