import Vue from 'vue';
import Router from 'vue-router';
import login from '../components/login/login';
import share from "../components/share";
import software from "../components/main/software";
import detail from "../components/main/detail"
import userDetail from "../components/main/userDetail"

import list from "../components/main/list"
import my from "../components/main/my"
import user from "../components/main/user"
import square from "../components/main/square"
import search from "../components/main/search"
import admin from "../components/manager/admin"
import author from "../components/manager/author"
import message from  "../components/main/message"
import userCollection from "../components/main/userCollection"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/share',
      name: 'share',
      component: share,
      redirect: '/software',
      children: [
        {path: '/software', component: software, name: "software"},
        {path: '/detail', component: detail, name: 'detail'},
        {path: '/list', component: list, name: 'list'},
        {path: '/my', component: my, name: 'my'},
        {path: '/user', component: user, name: 'user'},
        {path: '/square', component: square, name: 'square'},
        {path: '/search', component: search, name: 'search'},
        {path: '/admin', component: admin, name: 'admin'},
        {path: '/author', component: author, name: 'author'},
        {path: '/message', component: message, name: 'message'},
        {path: '/userDetail', component: userDetail, name: 'userDetail'},
        {path: '/userCollection', component: userCollection, name: 'userCollection'},

      ]
    },
    {
      path: '/',
      name: 'login',
      component: login,
    },

  ]
})
