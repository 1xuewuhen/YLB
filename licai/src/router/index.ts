import {createRouter, createWebHistory, type RouteRecordRaw} from 'vue-router'


const routes: RouteRecordRaw[] = [
    {
        path: '/',
        name: '',
        component: () => import('@/views/index/IndexView.vue')
    },
    {
        path: '/index',
        name: 'index',
        component: () => import('@/views/index/IndexView.vue')
    }
]
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: routes
})

export default router
