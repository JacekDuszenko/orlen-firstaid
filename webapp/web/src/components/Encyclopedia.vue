<template>
<div>
    <div class="encyclopedia-content-wrapper-left">
        <encyclopedia-record
            v-for="record in records"
            v-bind:key="record.id"
            v-bind:title="record.title"
            v-on:choose-record="currentRecord=record.content"
        >
        </encyclopedia-record>
    </div>
    <div class="encyclopedia-content-wrapper-right">
        <encyclopedia-content
            v-for="current in currentRecord"
            v-bind:type="current.type"
            v-bind:value="current.value"
        >
        </encyclopedia-content>
    </div>
    
</div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import EncyclopediaRecord from './EncyclopediaRecord.vue';
import EncyclopediaContent from './EncyclopediaContent.vue';
import firebase from '../Firebase';

Vue.component('encyclopedia-record',  EncyclopediaRecord);
Vue.component('encyclopedia-content',  EncyclopediaContent);

@Component({
    props: {
    records: Array,
  },
})
export default class Encyclopedia extends Vue {
  public data() {
    this.$props.records = [];
    firebase.firestore().collection('case').get()
    .then((querySnapshot) => {
      querySnapshot.forEach((doc) => {
        const data = {
          title: doc.data().title_pl,
          content: doc.data().content_pl,
        };
        this.$props.records.push(data);
      });
    });

    firebase.firestore().collection('case').onSnapshot((querySnapshot) => {
      querySnapshot.forEach((doc) => {
        const data = {
          title: doc.data().title_pl,
          content: doc.data().content_pl,
        };
        this.$props.records.push(data);
      });
    });

    return {
      currentRecord: null,
    };

  }

}

</script>
<style scoped>
div.encyclopedia-content-wrapper-left{
    heigth: 650px;
    width: 30%;
    float: left;
    border-right: 1px solid #e5403a;
    margin-top: 1px;
    margin-bottom: 1px;
}
div.encyclopedia-content-wrapper-right{
    float: left;
    heigth: 100%;
    width: 69%;
}
</style>

