import { createApp } from 'vue';
import App from '@/App.vue';
import cookies from 'vue-cookies';
import router from "@/router/router";
import store from '@/store';
import directives from "@/directives";

const app = createApp(App)

directives.forEach(directive => {
    app.directive(directive.name, directive)
})

app
    .use(cookies)
    .use(router)
    .use(store)
    .mount('#app')
