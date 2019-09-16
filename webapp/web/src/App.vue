<template id="app">
  <div class="app">
      <router-view></router-view>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class App extends Vue {}
</script>

<style>
body { 
  margin-left: 0;
  margin-right: 0;
  margin-bottom: 0;
}
.app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  text-align: center;
  padding:0px;
  margin:0px;
}
</style>
