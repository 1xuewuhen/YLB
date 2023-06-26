<template>
  <h2 class="public-title"><span>{{productTitle}}</span> <a href="list.html" target="_blank"
                                                    class="public-title-more">查看更多产品>></a>
  </h2>
  <ul class="preferred-select clearfix">
    <ProductTreasureView v-if="productTitle=='优选产品'" v-for="(item,index) in preferred" :key="item.id" :item="item! as Product" :index="index" :productTitle='"优选产品"'></ProductTreasureView>
    <ProductTreasureView v-if="productTitle=='散标产品'" v-for="(item,index) in scatterLabel" :key="item.id" :item="item! as Product" :index="index" :productTitle='"散标产品"'></ProductTreasureView>
  </ul>
  <!--    <li>
        <h3 class="preferred-select-title">
          <span>季度宝</span>
          <img src="@/assets/image/1-bg2.jpg" alt="">
        </h3>
        <div class="preferred-select-number">
          <p><b>6.2</b>%</p>
          <span>历史年化收益率</span>
        </div>
        <div class="preferred-select-date">
          <div>
            <span>投资周期</span>
            <p><b>1</b>个月</p>
          </div>
          <div>
            <span>余利可投资金额</span>
            <p class="preferred-select-date-no"><b>0.0</b>元</p>
          </div>
        </div>
        <p class="preferred-select-txt">
          优选计划项目，投资回报周期1个月，起点低，适合短期资金周转、对流动性要求高的投资人。
        </p>
        <a href="javascript:;" target="_blank" class="preferred-select-btn">立即投资</a>
      </li>
      <li>
        <h3 class="preferred-select-title">
          <span>双季宝</span>
          <img src="@/assets/image/1-bg3.jpg" alt="">
        </h3>
        <div class="preferred-select-number">
          <p><b>7.1</b>%</p>
          <span>历史年化收益率</span>
        </div>
        <div class="preferred-select-date">
          <div>
            <span>投资周期</span>
            <p><b>1</b>个月</p>
          </div>
          <div>
            <span>余利可投资金额</span>
            <p><b>250000.0</b>元</p>
          </div>
        </div>
        <p class="preferred-select-txt">
          优选计划项目，投资回报周期1个月，起点低，适合短期资金周转、对流动性要求高的投资人。
        </p>
        <a href="javascript:;" target="_blank" class="preferred-select-btn">立即投资</a>
      </li>-->


</template>
<script setup lang="ts">
import ProductTreasureView from "@/views/index/product/specificProduct/productTreasure/ProductTreasureView.vue";
import {Product} from '@/interface/typeInterface'


withDefaults(defineProps<{
  preferred: Array<Product>,
  scatterLabel: Array<Product>,
  productTitle:string
}>(),{
  preferred:[
    {
      id:0,
      productName:'',
      rate:0,
      cycle:0,
      releaseTime:0,
      productType:'',
      productNo:'',
      productMoney:0,
      leftProductMoney:0,
      bidMinLimit:0,
      bidMaxLimit:0,
      productStatus:0,
      productFullTime:0,
      productDesc:'',
      version:0
    }
  ],
  scatterLabel:[
    {
      id:0,
      productName:'',
      rate:0,
      cycle:0,
      releaseTime:0,
      productType:'',
      productNo:'',
      productMoney:0,
      leftProductMoney:0,
      bidMinLimit:0,
      bidMaxLimit:0,
      productStatus:0,
      productFullTime:0,
      productDesc:'',
      version:0
    }
  ],
  productTitle:'优选产品'
})

</script>

<style scoped>

</style>