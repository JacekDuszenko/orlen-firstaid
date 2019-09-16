<template>
<div class="encyclopedia-content">
  <p class="encyclopedia-content-text" v-if="type=='text'">{{ value }}</p>
  <img class="encyclopedia-content-picture" v-if="type=='picture'" v-bind:src="value"/>
  <div class="video-container"
  v-if="type=='video'" 
  > 
    <iframe width="640" height="360" v-bind:src="value" frameborder="0" allowfullscreen></iframe>
  </div>
</div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component({
  props: {
    type: String,
    value: String,
  },
})
export default class EncyclopediaContent extends Vue {

}

</script>

<style scoped>
div.encyclopedia-content{
  margin-top: 10px;
}
p.encyclopedia-content-text{
  font-size: 20px;
}
img.encyclopedia-content-picture{
  width: 60%;
}
.video-container {
    position: relative;
    padding-bottom: 56.25%; /*16:9*/  
    padding-top: 10px;
    height: 0;
    overflow: hidden;
}
.video-container iframe,
.video-container object,
.video-container embed {
    width: 70%;
}
</style>