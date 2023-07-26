<template>

  <HeaderView></HeaderView>
  <div class="content clearfix">
    <!--个人信息-->
    <div class="user-head">
      <div class="user-head-left fl">
        <div class="user-head-img">
          <img src="@/assets/image/user-head.png" alt="">
        </div>
        <p>上传头像</p>
      </div>
      <div class="user-head-right fl">
        <ul class="user-head-name fl">
          <li><b>{{ userAllAccountInfo.name }}</b></li>
          <li>{{ userAllAccountInfo.email }}</li>
          <li>最近登录：{{ userAllAccountInfo.lastLoginTime }}</li>
        </ul>
        <div class="user-head-money fr">
          <p>可用余额：<span>￥{{ userAllAccountInfo.availableMoney }}元</span></p>
          <router-link to="" @click="recharge" class="user-head-a1">充值</router-link>
          <router-link to="/index" class="user-head-a2">投资</router-link>
        </div>
      </div>

    </div>
    <!--记录-->
    <div class="user-record-box clearfix">
      <RecodeView :recode="bidInfosRecords" :sign="1"></RecodeView>
      <RecodeView :recode="records" :sign="2"></RecodeView>
      <RecodeView :recode="incomeRecords" :sign="3"></RecodeView>
    </div>


  </div>
  <FooterView></FooterView>
</template>
<script setup lang="ts">
import RecodeView from './recode/RecodeView.vue'
import HttpUtil from "@/api";
import {RecodeType, UserAllAccountInfoType} from "@/interface/typeInterface"
import {onMounted, ref} from "vue";
import {useRouter, useRoute} from "vue-router";
// import qs from 'qs'

const router = useRouter()
const route = useRoute()
const userAllAccountInfo = ref<UserAllAccountInfoType>({
  "id": 0,
  "phone": "",
  "email": "",
  "name": "",
  "lastLoginTime": "",
  "headerImage": "",
  "availableMoney": 0
})
const records = ref<RecodeType[]>([{
  "id": 0,
  "result": "",
  "rechargeDate": "",
  "rechargeMoney": 0
}])
const bidInfosRecords = ref<RecodeType[]>([{
  "id": 0,
  "result": "",
  "rechargeDate": "",
  "rechargeMoney": 0
}])
const incomeRecords = ref<RecodeType[]>([{
  "id": 0,
  "result": "",
  "rechargeDate": "",
  "rechargeMoney": 0
}])
const userinfo = JSON.parse(sessionStorage.getItem("userinfo"))
const uid = ref(0)
onMounted(async () => {
  await xxx()
})

const recharge = () => {
  router.push({
    path:'/userPay',
    query:{
      uid:uid.value
    }
  })
}
const xxx = async () => {
  uid.value = route.query.uid
  if (!uid.value) {
    uid.value = userinfo.uid
    await router.push({
      path: '/userCenter',
      query: {
        uid: uid.value
      }
    })
  }
  await HttpUtil.get(`/v1/user/UserAllAccountInfo?uid=${uid.value}`).then(value => {
    if (value.data.code == 1000) {
      userAllAccountInfo.value = value.data.data
    }
  })
  await HttpUtil.get(`/v1/recharge/records?uid=${uid.value}`).then(value => {
    if (value.data.code == 1000) {
      records.value = value.data.data.records
      bidInfosRecords.value = value.data.data.bidInfosRecords
      incomeRecords.value = value.data.data.incomeRecords
    }
  })
}
</script>


<style scoped>

</style>