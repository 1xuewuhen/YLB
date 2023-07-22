<template>
  <!--右侧-->
  <div class="detail-right">
    <div class="detail-right-title">立即投资</div>
    <div class="detail-right-mode">
      <h3 class="detail-right-mode-title">收益方式</h3>
      <p class="detail-right-mode-p"><span>到期还本付息</span></p>
      <h3 class="detail-right-mode-title">我的账户可用</h3>
      <div class="detail-right-mode-rmb" v-if="!loginEd">
        <p>资金（元）：******</p>
        <router-link to="/login">请登录</router-link>
      </div>
      <div class="detail-right-mode-rmb" v-else>
        <p>资金（元）：{{ userAllAccountInfo.availableMoney }}</p>
        <!--        <a href="javascript:void (0)">请登录</a>-->
      </div>
      <h3 class="detail-right-mode-title">预计利息收入（元） {{ income }}</h3>
      <form action="javascript:void (0)" id="number_submit">
        <p>请在下方输入投资金额</p>
        <input type="text" placeholder="请输入日投资金额，应为100元整倍数" v-model="investMoney" @blur="checkInvestMoney"
               class="number-money">
        <input type="button" @click="investProduct" value="立即投资" class="submit-btn">
      </form>
    </div>
  </div>
</template>
<script setup lang="ts">
import {onMounted, ref} from "vue";
import HttpUtil from "@/api";
import type {ProductInfoType, UserAllAccountInfoType} from "@/interface/typeInterface";
import layx from "vue-layx";
import {useRouter} from "vue-router";

const money = ref<number>(0)
const router = useRouter()
const loginEd = ref(false)
const userInfoString = sessionStorage.getItem("userinfo")
const userAllAccountInfo = ref<UserAllAccountInfoType>({
  "id": 0,
  "phone": "",
  "email": "",
  "name": "",
  "lastLoginTime": "",
  "headerImage": "",
  "availableMoney": 0
})
const investMoney = ref(100)
const income = ref('')
const defaults = withDefaults(defineProps<{
  productInfo: ProductInfoType
}>(), {
  productInfo: {
    "id": 1,
    "productName": "个人信用消费借款1",
    "rate": 5.9,
    "cycle": 9,
    "releaseTime": 1532361600000,
    "productType": 2,
    "productNo": "20170722",
    "productMoney": 100000,
    "leftProductMoney": 0,
    "bidMinLimit": 100,
    "bidMaxLimit": 100,
    "productStatus": 2,
    "productFullTime": 1609396361000,
    "productDesc": "个人消费借款，信用良好，购车消费，精英人士",
    "version": 4
  }
});

const emit = defineEmits<{
  (e:'refreshPage'):void
}>()
const investProduct = async () => {
  if (userInfoString) {
    const userinfo = JSON.parse(userInfoString);
    // 检查是否有实名认证
    if (userinfo.name != null && userinfo.name != '') {
      checkInvestMoney()
      await HttpUtil.post('/v1/invest/product', null, {
        productId: defaults.productInfo.id,
        money: investMoney.value
      }).then(value => {
        if (value.data.code == 1000) {
          layx.msg('投资成功', {
            dialogIcon: 'info'
          })
          queryUserInfo(userinfo.uid)
          emit('refreshPage')
        } else {
          layx.msg(value.data.msg, {
            dialogIcon: 'error'
          })
        }
      })
    } else {
      layx.msg('投资前需要进行实名认证', {
        dialogIcon: 'warn'
      })
    }
  } else {
    // 登录
    layx.msg('请先登录', {
      dialogIcon: 'warn'
    })
    await router.push({
      path: '/login'
    })
  }
}
const checkInvestMoney = () => {
  const regExp = new RegExp('^[1-9]\\d*$')
  if (regExp.test(investMoney.value) && investMoney.value >= 100 && investMoney.value % 100 == 0) {
    // 利息 = 本金* 周期* 利率
    // 日利率
    let dayRate = defaults.productInfo.rate / 365 / 100
    let incomeMoney = 0.0
    if (defaults.productInfo.productType == 0) {
      incomeMoney = investMoney.value * defaults.productInfo.cycle * dayRate
    } else {
      incomeMoney = investMoney.value * (defaults.productInfo.cycle * 30) * dayRate
    }
    income.value = incomeMoney.toFixed(2)
  } else {
    layx.msg('输入金额不合规,最小金额是100且金额必须是100的整数', {
      dialogIcon: 'error'
    })
    investMoney.value = 100
  }
}
const queryUserInfo = async (id: number) => {
  await HttpUtil.get(`/v1/user/UserAllAccountInfo?uid=${id}`).then(value => {
    if (value.data.code == 1000) {
      userAllAccountInfo.value = value.data.data
    }
  })
}

onMounted(async () => {
  if (userInfoString) {
    loginEd.value = true
    const userinfo = JSON.parse(userInfoString);
    await queryUserInfo(userinfo.uid)
  }
})


</script>

<style scoped>

</style>