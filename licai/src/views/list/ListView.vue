<template>
  <HeaderView></HeaderView>
  <div class="content clearfix">
    <!--排行榜-->
    <ul class="rank-list">
      <li v-for="(item,index) in rank" :key="item.phone">
        <img src="@/assets/image/list-rank1.png" v-if="index==0" alt="">
        <img src="@/assets/image/list-rank2.png" v-else-if="index==1" alt="">
        <img src="@/assets/image/list-rank3.png" v-else-if="index==2" alt="">
        <p class="rank-list-phone">{{ item.phone }}</p>
        <span>{{ item.money }}元</span>
      </li>
    </ul>
    <!--产品列表-->
    <ul class="preferred-select clearfix">
      <ProductTreasureView v-for="item in product" :key="item.id" :item="item"></ProductTreasureView>
    </ul>

    <PagingView :page="page" @on-click="getPageNo"></PagingView>

  </div>

  <FooterView></FooterView>
</template>

<script setup lang="ts">
import ProductTreasureView from "./productList/ProductListView.vue";
import {useRoute} from 'vue-router';
import {PageType, Product, RankType} from '@/interface/typeInterface'
import {onMounted, watch, ref} from "vue";
import HttpUtil from "@/api";
import layx from "vue-layx"

const route = useRoute()
const rank = ref<RankType[]>([{
  phone: '183******00',
  money: 2505
}])
const product = ref<Product[]>([
  {
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
  }
])
const page = ref<PageType>({
  pageNo: 1,
  pageSize: 0,
  totalPage: 0,
  totalRecord: 0
})
onMounted(async () => {
  await initPage(route.query.pType as number, 1)
  await HttpUtil.get('/v1/invest/rank').then(value => {
    if (value.data.code == 1000) {
      rank.value = value.data.list
    }
  })
})
watch([route], (value, oldValue, onCleanup) => {
  initPage(value[0].query.pType as number, 1)
}, {
  deep: true
})
const getPageNo = (pageNo: number) => {

  initPage(route.query.pType as number, pageNo)

}
const initPage = async (pType: number | string, pageNo: number) => {
  await HttpUtil.get('/v1/product/list', {pType: pType, pageNo: pageNo, pageSize: 9}).then(value => {
    if (value.data.code == 1000) {
      product.value = value.data.list
      page.value = value.data.page
    }
  })
}
</script>

<style scoped>

</style>