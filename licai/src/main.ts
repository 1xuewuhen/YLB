import '@/assets/css/details.css'
import '@/assets/css/font-awesome.min.css'
import '@/assets/css/index.css'
import '@/assets/css/list.css'
import '@/assets/css/login.css'
import '@/assets/css/public-head.css'
import '@/assets/css/reset.css'
import '@/assets/css/swiper.css'
import '@/assets/css/user_center.css'
import '@/assets/css/user_pay.css'
import HeaderView from "@/components/header/HeaderView.vue";
import FooterView from '@/components/footer/FooterView.vue'

import {createApp} from 'vue'
import {createPinia} from 'pinia'

import App from './App.vue'
import router from './router'


const app = createApp(App)

app.use(createPinia())
app.use(router)
app.component("HeaderView", HeaderView)
app.component("FooterView", FooterView)
app.mount('#app')
