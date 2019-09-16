<template>
<div class="box-1">
<div class="btn btn-one"
  v-on:click="$emit('choose-record')"
>
  <span>{{ title }}</span>
</div>
</div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component({
  props: {
    title: String,
  },
})
export default class EncyclopediaRecord extends Vue {

}

</script>

<style>
button.record{
  float: left;
  font-weight: bold;
  margin-top: 10px;
  font-size: 20px;
  text-align: center;
  height:60px;
  width:100%;
  margin-right: 10px;
  clear: both;  
}


.btn {
	line-height: 50px;
	height: 50px;
	text-align: center;
	width: 100%;
	cursor: pointer;
}

.btn-one {
	color: black;
	transition: all 0.3s;
	position: relative;
}
.btn-one span {
	transition: all 0.3s;
}
.btn-one::before {
	content: '';
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 1;
	opacity: 0;
	transition: all 0.3s;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #e5403a;
  border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #e5403a;
	transform: scale(0.1, 1);
}
.btn-one:hover span {
  color: #e5403a;
	letter-spacing: 2px;
}
.btn-one:hover::before {
	opacity: 1;	
	transform: scale(1, 1);	
}
.btn-one::after {
	content: '';
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 1;
	transition: all 0.3s;
	background-color: rgba(255,255,255,0.1);
}
.btn-one:hover::after {
	opacity: 0;	
	transform: scale(0.1, 1);
}
</style>