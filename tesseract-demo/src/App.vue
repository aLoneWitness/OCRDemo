<template>
  <div id="app">
    <vue-camera></vue-camera>
  </div>
</template>

<script>
import VueCamera from './components/vue-camera.vue'

export default {
  name: 'app',
  components: {
    VueCamera
  },
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
