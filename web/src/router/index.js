import Vue from 'vue'
import VueRouter from 'vue-router';
import Main from "@/components/index/Main";
import Book from "@/components/book/Book";
import PersonalCenter from "@/components/personalCenter/PersonalCenter";
import Info from "@/components/personalCenter/follow/Info";
import myArticle from "@/components/personalCenter/follow/myArticle";
import myCollect from "@/components/personalCenter/follow/myCollect";
import bookDetail from "@/components/book/bookDetail";
import category from "@/components/book/category";
import ex from "@/components/book/ex";
import Group_All from "@/components/group/GroupPost";
import article from "@/components/book/article";
import Login from "@/components/Login";
import GroupDetail from "@/components/group/GroupDetail";
import GroupFind from "@/components/group/GroupAll";
import GroupAll from "@/components/group/GroupAll";
import GroupPost from "@/components/group/MyGroup";
import BookConnect from "@/components/book/bookContent/bookContent";

Vue.use(VueRouter)


const routes = [
    {path: '/', redirect: '/login'},
    {path: '/login', component: Login},
    {
        path: '/main',
        redirect: '/book',
        name: '主界面',
        meta: {title: 'main'},
        component: Main,
        children: [
            {path: '/book', component: Book,},
            {path: "/book/bookDetail", component: bookDetail},
            {path: "/book/category", component: category,},
            {path: "/book/ex", component: ex,},
            {path: '/book/article', component: article},
            {path: '/group', component: GroupAll},
            {path: '/group/GroupDetail', component: GroupDetail},
            {path: '/group/group_all', component: Group_All},
            {path: '/group/GroupFind', component: GroupFind},
            {path: '/group/GroupPost', component: GroupPost},

        ],
    },
    {
        path: "/bookContent",
        name: '书籍内容分页面',
        component: BookConnect,
        children: []
    },
    {
        path: '/personCenter', component: PersonalCenter,
        children: [
            {path: '/personCenter/info', component: Info},
            {path: '/personCenter/myArticle', component: myArticle},
            {path: '/personCenter/myCollect', component: myCollect},
        ]
    },


    /*{
      path:"/login",
      name:'登录',
      meta:{title: '登录'},
      component:Login
    },
    {
      path:"/register",
      name:'注册',
      meta:{title:'注册'},
      component:Register
    }*/
]

const router = new VueRouter({
    // mode:'history',
    // base:process.env.BASE_URL,
    routes
})

export default router
