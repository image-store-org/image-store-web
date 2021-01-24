import {createRouter, createWebHistory} from 'vue-router'
import Home from './views/Home.vue'

const routes = [
    {
        path: '/',
        component: Home
    },
    {
        path: '/file',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "file" */ './views/File.vue'),
        children: [
            {
                path: 'upload',
                component: () => import(/* webpackChunkName: "upload" */ './components/Upload.vue')
            },
            {
                path: 'browse',
                component: () => import(/* webpackChunkName: "browse" */ './components/Browse.vue')
            }
        ]
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
