<template>
  <!--产品-->
  <div class="content">
    <h2 class="public-title"><span>新手宝</span></h2>
    <div class="new-user" v-for="noviceTreasure in product.noviceTreasure" :key="noviceTreasure.id">
      <div class="new-user-sm">
        <span>{{ noviceTreasure.bidMinLimit }}元起投</span>
        <span>投资最高限额{{ noviceTreasure.bidMaxLimit }}元</span>
        <span>当日即系</span>
      </div>
      <div class="new-user-number">
        <ul>
          <li>
            <p><b>{{ noviceTreasure.rate }}</b>%</p>
            <span>历史年化收益率</span>
          </li>
          <li>
            <p><b>{{ noviceTreasure.cycle }}</b>天</p>
            <span>投资周期</span>
          </li>
          <li>
            <p><b>{{ noviceTreasure.leftProductMoney }}</b>元</p>
            <span>余利可投资金额</span>
          </li>
        </ul>
        <router-link :to="{path:'/page/product/detail',query:{productId:noviceTreasure.id}}" class="new-user-btn">立即投资</router-link>
      </div>
      <span class="new-tag">新用户专享</span>
    </div>

    <SpecificProductView :product="product.preferred! as Product[]" :productTitle="'优选产品'"></SpecificProductView>
    <SpecificProductView :product="product.scatterLabel! as Product[]" :productTitle="'散标产品'"></SpecificProductView>
  </div>
</template>
<script setup lang="ts">
import {ref, onMounted} from "vue";
import SpecificProductView from "./specificProduct/SpecificProductView.vue";
import {ProductType,Product} from '@/interface/typeInterface'
import HttpUtil from "@/api";

const product = ref<ProductType>({noviceTreasure: [], preferred: [], scatterLabel: []})
onMounted(() => {
  HttpUtil.get('/v1/product/index').then(value => {
    if (value.data.code == 1000) {
      product.value = value.data.data
    }
  })
})

</script>


<style scoped>

</style>