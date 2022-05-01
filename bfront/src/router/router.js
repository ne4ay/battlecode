import {createRouter, createWebHistory} from "vue-router";
import HomeView from "@/views/HomeView";
import LoginView from "@/views/LoginView";
import LanguagesView from "@/views/LanguagesView";
import TasksView from "@/views/TasksView";

const routes = [
    {
        path: '/',
        component: HomeView
    },
    {
      path: '/auth',
      component: LoginView
    },
    {
        path: '/langs',
        component: LanguagesView
    },
    {
        path: '/tasks/:langName',
        component: TasksView
    }
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;