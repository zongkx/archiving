import Vue from 'vue';
import Router from 'vue-router';
import login from '../components/login/login';

import index  from "../components/index"
import user from "../components/manager/user"
import main from "../components/manager/main"
import role  from  "../components/manager/role"
import perm  from  "../components/manager/perm"

import software  from  "../components/content/software"
import share  from  "../components/content/share"
import comment  from  "../components/content/comment"
import issue  from  "../components/content/issue"



Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/index',
      name: 'index',
      component: index,
      redirect: '/main',
      children: [
        {path: '/main', component: main, name: "main"},
        {path: '/user', component: user, name: "user"},
        {path: '/role', component: role, name: "role"},
        {path: '/perm', component: perm, name: "perm"},
        {path: '/software', component: software, name: "software"},
        {path: '/share', component: share, name: "share"},
        {path: '/comment', component: comment, name: "comment"},
        {path: '/issue', component: issue, name: "issue"},
      ]
    },
    {
      path: '/',
      name: 'login',
      component: login,
    },


  ]
})
