import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Index',
        component: () =>
            import ('../views/Index.vue'),
        redirect: '/home',
        children: [
            {
                path: 'home',
                name: 'home',
                meta: { name: '系统首页' },
                component: () =>
                    import ('../views/home.vue')
            },
            {
                path: 'details',
                name: 'details',
                meta: { name: '玩具详情' },
                component: () =>
                    import ('../views/details.vue')
            },
            {
                path: 'display',
                name: 'display',
                meta: { name: '玩具对比' },
                component: () =>
                    import ('../views/display.vue')
            },
        ]
    },
    {
        path: '*',
        name: '404',
        component: () =>
            import ('../views/404.vue')
    },

]

const router = new VueRouter({
    routes
})

export default router
