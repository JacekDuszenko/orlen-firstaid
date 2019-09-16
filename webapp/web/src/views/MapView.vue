<template>
<div>
  <web-app-nav></web-app-nav>
  <web-app-map></web-app-map>
</div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import Map from '../components/Map.vue';
import Nav from '../components/Nav.vue';

Vue.component('web-app-nav', Nav);
Vue.component('web-app-map', Map);

@Component
export default class MapView extends Vue {

}
</script>