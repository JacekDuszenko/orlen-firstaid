<template>
<div class="mobile-encyclopedia">
	<div class="box-1">
		<div class="btn btn-one"
  		v-on:click="infoVisible=!infoVisible"
		>
  			<span>{{ title }}</span>
		</div>
	</div>
	<div v-if="infoVisible">
		<encyclopedia-content
            v-for="current in content"
            v-bind:type="current.type"
            v-bind:value="current.value"
        >
        </encyclopedia-content>
	</div>
</div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import EncyclopediaContent from './EncyclopediaContent.vue';

Vue.component('encyclopedia-content',  EncyclopediaContent);

@Component({
  props: {
    title: String,
	content: Array,
  },
})
export default class EncyclopediaMobileRecord extends Vue {
  public data() {
    return {
      infoVisible: false,
    };
  }
}

</script>
<style scoped>
div.mobile-encyclopedia{
  margin-top: 5px;
}
</style>
