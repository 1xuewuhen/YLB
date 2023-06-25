<template>
  <!--产品-->
  <div class="content">
    <h2 class="public-title"><span>新手宝</span></h2>
    <div class="new-user" v-for="noviceTreasure in product.noviceTreasure" :key="noviceTreasure.id">
      <div class="new-user-sm">
        <span>{{noviceTreasure.bidMinLimit}}元起投</span>
        <span>投资最高限额{{noviceTreasure.bidMaxLimit}}元</span>
        <span>当日即系</span>
      </div>
      <div class="new-user-number">
        <ul>
          <li>
            <p><b>{{noviceTreasure.rate}}</b>%</p>
            <span>历史年化收益率</span>
          </li>
          <li>
            <p><b>{{noviceTreasure.cycle}}</b>天</p>
            <span>投资周期</span>
          </li>
          <li>
            <p><b>{{ noviceTreasure.leftProductMoney }}</b>元</p>
            <span>余利可投资金额</span>
          </li>
        </ul>
        <a href="details.html" target="_blank" class="new-user-btn">立即投资</a>
      </div>
      <span class="new-tag">新用户专享</span>
    </div>

    <SpecificProductView></SpecificProductView>
    <SpecificProductView></SpecificProductView>

    <!--    <h2 class="public-title"><span>散标产品</span> <a href="list.html" target="_blank" class="public-title-more">查看更多产品>></a>
        </h2>
        <ul class="preferred-select clearfix">
          <li>
            <h3 class="preferred-select-title1">个人信用消费借款
              <span>散标</span>
            </h3>
            <div class="preferred-select-number">
              <p><b>4.9</b>%</p>
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
          </li>
          <li>
            <h3 class="preferred-select-title1">个人信用消费借款
              <span>散标</span>
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
            <h3 class="preferred-select-title1">个人信用消费借款
              <span>散标</span>
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
          </li>
        </ul>-->

  </div>
</template>
<script setup lang="ts">
import {ref, onMounted, computed} from "vue";
import SpecificProductView from "@/views/index/product/specificProduct/SpecificProductView.vue";
import {ProductType,Product1} from '@/interface/typeInterface'
import HttpUtil from "@/api";

const product = ref<ProductType>({noviceTreasure: [], preferred: [], scatterLabel: []})
onMounted(() => {
  HttpUtil.get('/v1/product/index').then(value => {
    if (value.data.code == 1000) {
      product.value = value.data.data
    }
  })
})
// const noviceTreasure = computed<Product1>(() => {
//   console.log(product.value.noviceTreasure)
//   return 'null'
// })
//
// const preferred = computed<>(() => {
//   return product.value.preferred
// })
//
// const scatterLabel = computed<>(() => {
//   return product.value.scatterLabel
// })
// console.log(noviceTreasure.value)
// console.log(preferred.value)
// console.log(scatterLabel.value)
</script>


<style scoped>

</style>