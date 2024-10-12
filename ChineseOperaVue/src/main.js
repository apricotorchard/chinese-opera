import './assets/styles/index.scss'
import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElIconModules from '@element-plus/icons-vue'
// import OperaIcon from '@/base/opera-icon/opera-icon.vue';


const app = createApp(App)
// 统一注册Icon图标
for (const iconName in ElIconModules) {
  if (Reflect.has(ElIconModules, iconName)) {
    const item = ElIconModules[iconName]
    app.component(iconName, item)
  }
}
// app.component('OperaIcon', OperaIcon)
app.use(createPinia())
app.use(router)
app.use(ElementPlus)

app.mount('#app')
