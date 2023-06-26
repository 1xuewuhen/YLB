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
        component: () => import('@/views/List/ListView.vue')
    }, {
        path: '/page/product/detail',
        name: 'pageProductDetail',
        component: () => import('@/views/Detail/DetailView.vue')
    }
]
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: routes
})

export default router
