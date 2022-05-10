import {createRouter, createWebHistory} from "vue-router";
import HomeView from "@/views/HomeView";
import LoginView from "@/views/LoginView";
import LanguagesView from "@/views/LanguagesView";
import TasksView from "@/views/TasksView";
import ErrorView from "@/views/ErrorView";
import AdminTasksView from "@/views/admin/AdminTasksView";
import AdminPanelView from "@/views/admin/AdminPanelView";
import AdminTaskAddingView from "@/views/admin/AdminTaskAddingView";
import SingleTaskView from "@/views/SingleTaskView";
import AdminUsersView from "@/views/admin/AdminUsersView";
import RatingView from "@/views/RatingView";
import AdminUserChangingView from "@/views/admin/AdminUserChangingView";

const routes = [
    {
        path: '/',
        alias: '/home',
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
    },
    {
        path: '/error',
        component: ErrorView
    },
    {
        path: '/admin',
        component: AdminPanelView,
    },
    {
        path: '/admin/tasks',
        component: AdminTasksView
    },
    {
        path: '/admin/task/add',
        component: AdminTaskAddingView
    },
    {
        path: '/admin/task/update/:id',
        component: AdminTaskAddingView
    },
    {
        path: '/task/:id',
        component: SingleTaskView
    },
    {
        path: '/admin/users',
        component: AdminUsersView
    },
    {
        path: '/admin/user/:id',
        component: AdminUserChangingView
    },
    {
        path: '/rating',
        component: RatingView
    }
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;