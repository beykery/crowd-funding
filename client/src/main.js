import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueRouter from 'vue-router';
import routeConfig from './router-config';
import App from './App.vue';
import Vuex from 'vuex'
import vuexConfig from './vuex-config';
import axios from 'axios';

Vue.prototype.$ajax = axios;

Vue.use(ElementUI);
Vue.use(VueRouter);
Vue.use(Vuex);

//定义状态
const store = new Vuex.Store(
  vuexConfig
);

//定义路由
const router = new VueRouter({
  mode: 'history',
  //mode: 'hash',//垃圾！
  routes: routeConfig
});
//标题
router.afterEach((to, from) =>
{
  console.info(from.path + " ---> " + to.path);
  document.title = to.name;
});
//vue
var vue = new Vue({
  router,
  store,
  el: '#app',
  render: h => h(App)
});
