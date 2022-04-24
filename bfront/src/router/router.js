import {createRouter, createWebHistory} from "vue-router";
import HomeView from "@/views/HomeView";
import LoginView from "@/views/LoginView";

const routes = [
    {
        path: '/',
        component: HomeView
    },
    {
      path: '/auth',
      component: LoginView
    },
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;