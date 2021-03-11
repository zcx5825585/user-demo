import Vue from 'vue'
import Router from 'vue-router'
import userPage from '@/components/userPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'userPage',
      component: userPage
    }

  ]
})
