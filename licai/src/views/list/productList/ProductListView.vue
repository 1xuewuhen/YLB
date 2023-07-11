<template>
  <li>
    <h3 class="preferred-select-title">
      <span>{{ item.productName }}</span>
      <img src="@/assets/image/1-bg1.jpg" alt="">
    </h3>
    <div class="preferred-select-number">
      <p><b>{{ item.rate }}</b>%</p>
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
      优选计划项目，投资回报周期{{ item.cycle }}个月，起点低，适合短期资金周转、对流动性要求高的投资人。
    </p>
    <router-link :to="{path:'/page/product/detail',query:{productId:item.id}}" class="preferred-select-btn">立即投资
    </router-link>
  </li>
</template>
<script setup lang="ts">
import {type Product} from '@/interface/typeInterface'
import {useRoute} from "vue-router";

const route = useRoute()

withDefaults(defineProps<
        {
          item: Product
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
      }
    }
)
</script>


<style scoped>

</style>