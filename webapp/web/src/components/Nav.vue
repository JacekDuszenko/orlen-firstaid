<template>
<div class="app-nav">
  <img class="app-nav" src="https://m.biznesradar.pl/0018af54265956930673dba0f8649a02,900,0,0,0.png"/>
  <p class="flex">
    <router-link to="/" class="bttn"><span>MAPA</span></router-link>
  </p>
  <p class="flex">
    <router-link to="/encyclopedia" class="bttn"><span>ENCYKLOPEDIA</span></router-link>
  </p>
</div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';

@Component
export default class Nav extends Vue {

}

</script>

<style scoped>
div.app-nav{
  width: 100%;
  float: left;
  border-bottom: 1px solid #e5403a;
  clear: both;  
  -webkit-font-smoothing: antialiased;
}
img.app-nav{
  width: 100px;
  float: left;
}
p{
  float: left;
  font-weight: bold;
  margin-top: 10px;
  font-size: 20px;
  text-align: center;
  height:30px;
  margin-right: 10px;
}

*,
*::before,
*::after {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

.flex {
  display:flex;
  align-items:center;
  justify-content:center;
}

a.bttn {
  color:#e5403a;
  text-decoration:none;
  -webkit-transition:0.3s all ease;
  transition:0.3s ease all;
}
a.bttn:hover {
    color:#FFF;
  }
a.bttn:focus {
    color:#FFF;
  }

.bttn {
  font-size:18px;
  letter-spacing:2px;
  text-transform:uppercase;
  display:inline-block;
  text-align:center;
  width:270px;
  font-weight:bold;
  padding:14px 0px;
  border:3px solid #e5403a;
  border-radius:2px;
  position:relative;
  box-shadow: 0 2px 10px rgba(0,0,0,0.16), 0 3px 6px rgba(0,0,0,0.1);
}
.bttn:before {
    -webkit-transition:0.5s all ease;
    transition:0.5s all ease;
    position:absolute;
    top:0;
    left:50%;
    right:50%;
    bottom:0;
    opacity:0;
    content:'';
    background-color:#e5403a;
    z-index:-2;
  }
.bttn:hover:before {
      -webkit-transition:0.5s all ease;
      transition:0.5s all ease;
      left:0;
      right:0;
      opacity:1;
  }
.bttn:focus:before {
      transition:0.5s all ease;
      left:0;
      right:0;
      opacity:1;
}

</style>