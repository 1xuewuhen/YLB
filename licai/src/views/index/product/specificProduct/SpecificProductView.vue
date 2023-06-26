<template>
  <h2 class="public-title"><span>{{ productTitle }}</span>
    <router-link :to='{path:"/page/product/list",query:{pType:productTitle=="优选产品"?1:2}}'
                 class="public-title-more">查看更多产品
    </router-link>
  </h2>
  <ul class="preferred-select clearfix">
    <ProductTreasureView v-for="(item,index) in product" :key="item.id"
                         :item="item! as Product" :index="index" :sign='productTitle=="优选产品"?"1":"2"'></ProductTreasureView>
  </ul>
</template>
<script setup lang="ts">
import ProductTreasureView from "./productTreasure/ProductTreasureView.vue";
import {Product} from '@/interface/typeInterface'


withDefaults(defineProps<{
  product: Array<Product>,
  productTitle: string
}>(), {
  product: [
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
  ],
  productTitle: '优选产品'
})

</script>

<style scoped>

</style>