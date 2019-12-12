import Vue from 'vue'
import App from './App.vue'
import LoadScript from 'vue-plugin-load-script';
 

Vue.config.productionTip = false

Vue.use(LoadScript);

Vue.loadScript("https://cdn.rawgit.com/naptha/tesseract.js/1.0.10/dist/tesseract.js")

new Vue({
  render: function (h) { return h(App) },
}).$mount('#app')
