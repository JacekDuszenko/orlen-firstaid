<template>
  <div id="map-view">
    <l-map
      :zoom="zoom"
      :center="center"
      :minZoom="minZoom"
    >
      <l-tile-layer :url="url"></l-tile-layer>
      <l-marker 
      v-for="accident in accidents" 
      v-bind:key="accident.id"
      :lat-lng="[accident.place._lat, accident.place._long]"
      >
        <l-popup :content="accident.street"></l-popup>
        <l-icon
          :icon-anchor="staticAnchor"
        >
          <img class="app-marker" src="../assets/crash.gif">
        </l-icon>
      </l-marker>
    </l-map>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';
import {LMap, LTileLayer, LMarker, LCircle, LIcon, LPopup} from 'vue2-leaflet';
import firebase from '../Firebase';

Vue.component('l-map', LMap);
Vue.component('l-tile-layer', LTileLayer);
Vue.component('l-marker', LMarker);
Vue.component('l-icon', LIcon);
Vue.component('l-circle', LCircle);
Vue.component('l-popup', LPopup);

@Component
export default class Map extends Vue {
  public data() {
    let temp: object[] = [];
    firebase.firestore().collection('accidents').get()
    .then((querySnapshot) => {
      querySnapshot.forEach((doc) => {
        const data = {
          place: doc.data().place,
          street: doc.data().street,
        };
        temp.push(data);
      });
    });

    firebase.firestore().collection('accidents').onSnapshot((querySnapshot) => {
      temp = [];
      querySnapshot.forEach((doc) => {
        const data = {
          place: doc.data().place,
          street: doc.data().street,
        };
        temp.push(data);
      });
    });

    return {
      url: 'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
      zoom: 15,
      minZoom: 5,
      accidents: temp,
      center: [52.232307, 21.011932],
    };
  }

}

</script>

<style scoped>
#map-view{
 clear:both;   
 height: 650px;
}
img.app-marker{
  width: 50px;
}
</style>
