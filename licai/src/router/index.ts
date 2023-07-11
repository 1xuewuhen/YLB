import {createRouter, createWebHistory, type RouteRecordRaw} from 'vue-router'

const selectEnvType = (): string => {
    if (import.meta.env.DEV) {
        return import.meta.env.BASE_URL
    } else if (import.meta.env.PROD) {
        if (import.meta.env.VITE_TYPE == 1) {
            return import.meta.env.VITE_TOMCAT_CONTEXT_PATH
        } else {
            return import.meta.env.VITE_NGINX_CONTEXT_PATH
        }
    }
}


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
    history: createWebHistory(selectEnvType()),
    routes: routes,
})
export default router
