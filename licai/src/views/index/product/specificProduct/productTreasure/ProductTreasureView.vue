<template>
  <li>
    <h3 v-if="sign=='1'" class="preferred-select-title">
      <span>{{ item.productName }}</span>
      <img src="@/assets/image/1-bg1.jpg" alt="" v-if="index==0">
      <img src="@/assets/image/1-bg2.jpg" alt="" v-else-if="index==1">
      <img src="@/assets/image/1-bg3.jpg" alt="" v-else>
    </h3>
    <h3 v-else-if="sign=='2'" class="preferred-select-title1">
      {{ item.productName }}
      <span>散标</span>
    </h3>
    <div class="preferred-select-number">
      <p><b>{{ item.rate! }}</b>%</p>
      <span>历史年化收益率</span>
    </div>
    <div class="preferred-select-date">
      <div>
        <span>投资周期</span>
        <p><b>{{ item.cycle }}</b>个月</p>
      </div>
      <div>
        <span>余利可投资金额</span>
        <p><b>{{ item.leftProductMoney }}</b>元</p>
      </div>
    </div>
    <p class="preferred-select-txt">
      {{ item.productDesc }}
    </p>
    <router-link :to='{path:"/page/product/detail",query:{productId:item.id}}' class="preferred-select-btn">立即投资
    </router-link>
  </li>
</template>

<script setup lang="ts">
import {Product} from "@/interface/typeInterface";

withDefaults(defineProps<{
  item: Product,
  index: number,
  sign: string
}>(), {
  item: {
    id: 0,
    productName: '',
    rate: 0,
    cycle: 0,
    releaseTime: 0,
    productType: '',
    productNo: '',
    productMoney: 0,
    leftProductMoney: 0,
    bidMinLimit: 0,
    bidMaxLimit: 0,
    productStatus: 0,
    productFullTime: 0,
    productDesc: '',
    version: 0
  },
  index: 0,
  sign: '1'
})

</script>


<style scoped>

</style>