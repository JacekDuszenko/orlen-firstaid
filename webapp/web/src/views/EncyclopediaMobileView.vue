<template>
<div>
  <encyclopedia-mobile-record
            v-for="record in records"
            v-bind:key="record.id"
            v-bind:title="record.title"
            v-bind:content="record.content"
        >
        </encyclopedia-mobile-record>
</div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import EncyclopediaMobileRecord from '../components/EncyclopediaMobileRecord.vue';
import firebase from '../Firebase';

Vue.component('encyclopedia-mobile-record',  EncyclopediaMobileRecord);

@Component
export default class EncyclopediaMobileView extends Vue {
  public data() {
    let temp: object[] = [];
    firebase.firestore().collection('case').get()
    .then((querySnapshot) => {
      querySnapshot.forEach((doc) => {
        const data = {
          title: doc.data().title_pl,
          content: doc.data().content_pl,
        };
        temp.push(data);
      });
    });

    firebase.firestore().collection('case').onSnapshot((querySnapshot) => {
      temp = [];
      querySnapshot.forEach((doc) => {
        const data = {
          title: doc.data().title_pl,
          content: doc.data().content_pl,
        };
        temp.push(data);
      });
    });

    return {
      records: temp,
    };
  }
}

</script>