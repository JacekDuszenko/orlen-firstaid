<template>
<div>
  <web-app-nav></web-app-nav>
  <web-app-encyclopedia></web-app-encyclopedia>
</div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import Nav from '../components/Nav.vue';
import Encyclopedia from '../components/Encyclopedia.vue';

Vue.component('web-app-nav', Nav);
Vue.component('web-app-encyclopedia', Encyclopedia);

@Component
export default class EncyclopediaView extends Vue {

}

</script>