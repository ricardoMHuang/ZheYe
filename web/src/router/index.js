import Vue from 'vue'
import VueRouter from 'vue-router';
import Main from "@/components/index/Main";
import Book from "@/components/book/Book";
import PersonalCenter from "@/components/personalCenter/PersonalCenter";
import Info from "@/components/personalCenter/follow/Info";
import myArticle from "@/components/personalCenter/follow/myMessage";
import myCollect from "@/components/personalCenter/follow/MyBookshelf";
import bookDetail from "@/components/book/BookDetail";
import category from "@/components/book/bookComponent/Category";
import ex from "@/components/book/ex";
import article from "@/components/book/ArticleDetail";
import Login from "@/components/Login";
import GroupFind from "@/components/group/Group";
import GroupPost from "@/components/group/GroupPost";
import BookConnect from "@/components/book/bookContent";
import PostEditor from "@/components/group/PostEditor"
import MyGroups from "@/components/group/MyGroupAPost";
import Group from "@/components/group/Group";
import GroupDetail from "@/components/group/GroupDetail";
import ArticleEditor from "@/components/personalCenter/ArticleEditor";
import GroupManage from "@/components/personalCenter/follow/GroupManage";
import CreateGroup from "@/components/personalCenter/follow/groupManage/CreatedGroup";
import GroupEditor from "@/components/personalCenter/GroupEditor";

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
            {path: '/group', component: Group},
            {path: '/group/myGroups', component: MyGroups},
            {path: '/group/groupDetail', component: GroupDetail},
            {path: '/group/GroupFind', component: GroupFind},
            {path: '/group/GroupPost', component: GroupPost},
            {path: '/group/postEditor', component: PostEditor},
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
            {path: '/personCenter/groupManage', component: GroupManage},
            {path: '/personCenter/createGroup', component: CreateGroup},
        ]
    },
    {path: '/articleEditor', component: ArticleEditor,},
    {path: '/groupEditor', component: GroupEditor,},


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
