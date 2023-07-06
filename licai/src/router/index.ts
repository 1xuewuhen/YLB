import {createRouter, createWebHistory, type RouteRecordRaw} from 'vue-router'


const routes: RouteRecordRaw[] = [
    {
        path: '/index',
        name: 'index',
        alias: ['/'],
        component: () => import('@/views/index/IndexView.vue')
    }, {
        path: '/page/product/list',
        name: 'pageProductList',
        component: () => import('@/views/list/ListView.vue')
    }, {
        path: '/page/product/detail',
        name: 'pageProductDetail',
        component: () => import('@/views/detail/DetailView.vue')
    }, {
        path: '/login',
        name: 'login',
        component: () => import('@/views/login/LoginView.vue')
    }, {
        path: '/register',
        name: 'register',
        component: () => import('@/views/register/RegisterView.vue')
    }, {
        path: '/realName',
        name: 'realName',
        component: () => import('@/views/realName/RealNameView.vue')
    }, {
        path: '/userCenter',
        name: 'userCenter',
        component: () => import('@/views/userCenter/UserCenterView.vue')
    }
]
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: routes
})

export default router
