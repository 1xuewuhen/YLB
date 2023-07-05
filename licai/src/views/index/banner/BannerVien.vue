<template>
  <!--banner-->
  <div class="banner-content">
    <div class="swiper-container banner-lb">
      <div class="swiper-wrapper">
        <div class="swiper-slide">
          <a href="javascript:;">
            <img src="@/assets/image/banner.jpg" alt="">
          </a>
        </div>
        <div class="swiper-slide">
          <a href="javascript:;">
            <img src="@/assets/image/banner.jpg" alt="">
          </a>
        </div>
      </div>
    </div>
    <div class="banner-abs">
      <div class="banner-abs-box">
        <div class="banner-abs-title">动力金融网历史年化收益率</div>
        <b>{{ platInfoStore.platInfo.historyAvgRate }}<i>%</i></b>
        <p>平台用户数</p>
        <span>{{ platInfoStore.platInfo.registerUsers }}<i>位</i></span>
        <p class="banner-abs-border">累计成交金额</p>
        <span>{{ platInfoStore.platInfo.sumBigMoney }}<i>元</i></span>
      </div>
    </div>
  </div>
  <div class="banner-list">
    <ul>
      <li>
        <img src="@/assets/image/banner-tag1.png" alt="">
        <p>
          <b>实力雄厚</b>
          <span>亿级注册资本 ,千万技术投入</span>
        </p>
      </li>
      <li>
        <img src="@/assets/image/banner-tag2.png" alt="">
        <p>
          <b>风控严苛</b>
          <span>30道风控工序，60项信用审核</span>
        </p>
      </li>
      <li>
        <img src="@/assets/image/banner-tag3.png" alt="">
        <p>
          <b>投资省心</b>
          <span>资金安全风控，银行安全托管</span>
        </p>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import {PlatInfoStore} from '@/stores'
const platInfoStore = PlatInfoStore()


</script>


<style scoped>

</style>