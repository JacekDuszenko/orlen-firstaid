import Vue from 'vue';
import App from './App.vue';
import { Icon } from 'leaflet';
import 'leaflet/dist/leaflet.css';
import router from './router';

Vue.config.productionTip = false;

Icon.Default.mergeOptions({
  iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
  iconUrl: require('leaflet/dist/images/marker-icon.png'),
  shadowUrl: require('leaflet/dist/images/marker-shadow.png'),
});

const app = new Vue({
    router,
    render: (createEle) => createEle(App),
}).$mount('#app');
