<template>
  <!--头部-->
  <div class="public-head">
    <div class="public-head-nav">
      <div class="public-head-left">
        <h1 class="public-head-logo">
          <router-link to="/">
            <img src="@/assets/image/logo.png" alt="">
          </router-link>
        </h1>
        <ul class="public-head-list">
          <li>
            <router-link to="/">主页</router-link>
          </li>
          <li class="public-head-hover">
            <a href="javascript:void(0);">我要投资</a>
            <!--二级导航-->
            <div class="two-title">
              <router-link :to="{path:'/page/product/list',query:{pType: 1}}">优选类产品</router-link>
              <router-link :to="{path:'/page/product/list',query:{pType: 2}}">散标类产品</router-link>
            </div>
          </li>
          <li><a href="javascript:void (0)">借款人信息</a></li>
          <li><a href="javascript:void (0)">信息披露</a></li>
          <li><a href="javascript:void (0)">安全计划</a></li>
        </ul>
      </div>
      <div class="public-head-right" v-if="loginEd">
<!--        <router-link to="/login">登录</router-link>-->
<!--        <router-link to="/register">注册</router-link>-->
        <router-link to="/realName">实名认证</router-link>
        <router-link to="" @click="userCenter">用户中心</router-link>
      </div>
      <div class="public-head-right" v-else>
                <router-link to="/login">登录</router-link>
                <router-link to="/register">注册</router-link>
<!--        <router-link to="/realName">实名认证</router-link>-->
<!--        <router-link to="" @click="userCenter">用户中心</router-link>-->
      </div>
    </div>
  </div>
  <!--end-->
</template>

<script setup lang="ts">
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";

const router = useRouter()
const userinfo = JSON.parse(<string>sessionStorage.getItem('userinfo'));
const loginEd = ref(false)

onMounted(() => {
  if (userinfo){
    loginEd.value = true
  }
})
const userCenter = () => {
  if (!userinfo) {
    router.push({
      path: '/login'
    })
  } else {
    router.push({
      path: '/userCenter',
      query: {
        uid: userinfo.uid
      }
    })
  }

}
</script>


<style scoped>

</style>