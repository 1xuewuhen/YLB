<template>

  <HeaderView></HeaderView>
  <!--充值-->
  <div class="user-pay-box clearfix">
    <div class="user-pay-left fl">
      <p class="user-pay-title1">我的账户</p>
      <div class="user-pay-list">
        <a href="javascript:void (0)" class="user-pay-list-on">充值</a>
        <a href="javascript:void (0)">我的信息</a>
        <a href="javascript:void (0)">投资记录</a>
        <a href="javascript:void (0)">收益记录</a>
      </div>
    </div>
    <div class="user-pay-right fl">
      <p class="user-pay-title2">第三方支付平台</p>
      <div class="user-pay-form">
        <img src="@/assets/image/pay-1.jpg" alt="">
        <form action="javascript:void (0)" id="money_submit">
          <p class="user-pay-form-ts">请输入金额</p>
          <input type="text" v-model="money" placeholder="元" name="" class="number-money">
          <input type="button" @click="payMoney" value="支付" class="submit-btn">
        </form>
      </div>
      <div class="user-pay-sm">
        <h3>温馨提示</h3>
        <p>1.为了您的使用安全，充值操作建议不要使用公共电脑。</p>
        <p>2.银行卡充值限额，由各银行限制。</p>
        <p>3.品台禁止用卡套现、虚拟交易、洗钱等行为，一经发现并确认，将终止该账户的使用。</p>
        <p>4.如果充值中出现问题，请联系客服400-890-0000。</p>
      </div>
    </div>

  </div>
  <FooterView></FooterView>
</template>

<script setup lang="ts">
import {ref} from "vue";
import {useRouter, useRoute} from "vue-router";

const router = useRouter()
const route = useRoute()
const money = ref(100)

const uid = route.query.uid
const payMoney = async () => {
  window.location.href = `http://127.0.0.1:8000/api/v1/pay/recharge?money=${money.value}&uid=${uid}`
}
</script>


<style scoped>

</style>