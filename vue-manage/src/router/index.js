import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/index',
        name: 'index',
        meta: { name: '主页' },
        component: () =>
            import ('../views'),
        redirect: '/index/user',
        children: [
            {
                path: 'user',
                name: 'user',
                meta: { name: '用户信息' },
                component: () =>
                    import ('../views/user.vue')
            },
            {
                path: 'toy',
                name: 'toy',
                meta: { name: '玩具信息' },
                component: () =>
                    import ('../views/toy.vue')
            },
            {
                path: 'toyBrand',
                name: 'toyBrand',
                meta: { name: '玩具品牌' },
                component: () =>
                    import ('../views/toyBrand.vue')
            },
            {
                path: 'toyMaterial',
                name: 'toyMaterial',
                meta: { name: '玩具材质' },
                component: () =>
                    import ('../views/toyMaterial.vue')
            },
            {
                path: 'toyType',
                name: 'toyType',
                meta: { name: '玩具类型' },
                component: () =>
                    import ('../views/toyType.vue')
            },
            {
                path:'toyChart',
                name:'toyChart',
                meta:{name:'图表'},
                component:()=>
                    import('../views/toyChart.vue')
            }
        ]
    },
    {
        path: '/',
        name: 'login',
        meta: { name: '登录' },
        component: () =>
            import ('../views/login.vue')
    },
    {
        path: '/register',
        name: 'register',
        meta: { name: '注册' },
        component: () =>
            import ('../views/register.vue')
    },
    {
        path: '*',
        name: '404',
        meta: { name: '404' },
        component: () =>
            import ('../views/404.vue')
    },

]


const router = new VueRouter({
    routes
})

export default router
