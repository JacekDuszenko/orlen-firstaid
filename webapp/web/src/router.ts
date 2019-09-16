import Vue from 'vue';
import Router from 'vue-router';
import Map from './components/Map.vue';
import MapView from './views/MapView.vue';
import EncyclopediaMobileView from './views/EncyclopediaMobileView.vue';
import EncyclopediaView from './views/EncyclopediaView.vue';

Vue.use(Router);

export default new Router({
  routes: [{
    path: '/',
    name: 'home',
    component: MapView,
},
{
    path: '/onlymap',
    name: 'onlymap',
    component: Map,
},
{
    path: '/onlyencyclopedia',
    name: 'onlyencyclopedia',
    component: EncyclopediaMobileView,
},
{
    path: '/encyclopedia',
    name: 'encyclopedia',
    component: EncyclopediaView,
}],
});
