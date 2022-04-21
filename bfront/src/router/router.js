import HomeView from "@/views/HomeView";
import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {
        path: '/',
        component: HomeView
    }
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;