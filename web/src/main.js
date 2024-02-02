import store from './store'
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import router from './router'
import axios from "axios";

require("./mock")
Vue.use(ElementUI)
Vue.prototype.$http = axios


Vue.config.productionTip = false
/*require("./mock")*/  //引入mock数据
new Vue({
    router,
    store,
    el: '#app',
    render: h => h(App)
});

