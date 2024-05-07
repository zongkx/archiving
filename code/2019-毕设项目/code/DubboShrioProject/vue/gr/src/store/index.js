
import Vue from 'vue';
import Vuex from 'vuex';
import  createPersistedState from 'vuex-persistedstate';
Vue.use(Vuex);
const state={   //要设置的全局访问的state对象
  showFooter: true,
  changableNum:0,
  isLogin:false,
  software:'',
  type:'',
  user:'',
  unReadNum:0,
  qry:'',
  cf: '',
  aid : '',
  //要设置的初始属性值
};
const getters = {   //实时监听state值的变化(最新状态)
  isShow(state) {  //承载变化的showFooter的值
    return state.showFooter
  },
  getChangedNum(){  //承载变化的changebleNum的值
    return state.changableNum
  }
};
const mutations = {
  show(state) {   //自定义改变state初始值的方法，这里面的参数除了state之外还可以再传额外的参数(变量或对象);
    state.isLogin = true;
  },
  hide(state) {  //同上
    state.isLogin = false;
  },
  newNum(state,sum){ //同上，这里面的参数除了state之外还传了需要增加的值sum
    state.changableNum+=sum;
  },
  software(state,sf){
    state.software = sf;
  },
  type(state,type){
    state.type = type;
  },
  user(state,user){
    state.user = user;
  },
  num(state,num){
    state.unReadNum = num;
  },
  qry(state,qry){
    state.qry = qry;
  },
  cf(state,cf){
   state.cf = cf;
  },
  aid(state,aid){
    state.aid = aid;
  },





};
const actions = {
  isLogin(context) {  //自定义触发mutations里函数的方法，context与store 实例具有相同方法和属性
    context.commit('show');
  },
  notLogin(context) {  //同上注释
    context.commit('hide');
  },
  getNewNum(context,num){   //同上注释，num为要变化的形参
    context.commit('newNum',num)
  },
  software(context,sf){
    context.commit('software',sf)
  },
  unReadNum(context,num){
    context.commit =('num',num) ;
  }
};
const store = new Vuex.Store({
  state,
  getters,
  mutations,
  actions,
  plugins: [createPersistedState({
    storage: window.sessionStorage
})]
});
export default store;
