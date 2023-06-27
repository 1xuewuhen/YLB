<template>
  <HeaderView></HeaderView>

  <div class="content clearfix">

    <div class="detail-left">
      <div class="detail-left-title">{{ productInfo.productName }}（{{ productInfo.productNo }}期）</div>
      <ul class="detail-left-number">
        <li>
          <span>历史年化收益率</span>
          <p><b>{{ productInfo.rate }}</b>%</p>
          <span>历史年化收益率</span>
        </li>
        <li>
          <span>募集金额（元）</span>
          <p><b>{{ productInfo.productMoney }}</b>元</p>
          <span v-if="productInfo.leftProductMoney == 0">已满标</span>
          <span v-else>募集中&nbsp;&nbsp;剩余募集金额{{ productInfo.leftProductMoney }}元</span>
        </li>
        <li>
          <span>投资周期</span>
          <p v-if="productInfo.productType==0"><b>{{ productInfo.cycle }}</b>天</p>
          <p v-else><b>{{ productInfo.cycle }}</b>个月</p>
        </li>
      </ul>
      <div class="detail-left-way">
        <span>收益获取方式</span>
        <span>收益返还：<i>到期还本付息</i></span>
      </div>
      <!--投资记录-->
      <InvestmentRecordsView :bidInfoList="bidInfoList"></InvestmentRecordsView>
    </div>
    <!--右侧-->
    <RightView></RightView>
  </div>
  <FooterView></FooterView>
</template>

<script setup lang="ts">
import {useRoute} from "vue-router";
import RightView from "./right/RightView.vue";
import InvestmentRecordsView from "./investmentRecords/InvestmentRecordsView.vue";
import {onMounted, ref} from "vue";
import {BidInfoListType, ProductInfoType} from '@/interface/typeInterface'
import HttpUtil from "@/api";

const productInfo = ref<ProductInfoType>({
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
})
const bidInfoList = ref<BidInfoListType[]>([
  {
    "id": 3227,
    "phone": "135******05",
    "bidTime": "2020-08-20 15:09:52",
    "bidMoney": 100
  }
])
const route = useRoute()
onMounted(() => {
  HttpUtil.get('/v1/product/info', {
    productId: route.query.productId
  }).then(value => {
    if (value.data.code == 1000) {
      productInfo.value = value.data.data
      bidInfoList.value = value.data.list
    }
  })
})
</script>

<style scoped>

</style>