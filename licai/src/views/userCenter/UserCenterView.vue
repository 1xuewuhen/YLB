<template>

  <HeaderView></HeaderView>
  <div class="content clearfix">
    <!--个人信息-->
    <div class="user-head">
      <div class="user-head-left fl">
        <div class="user-head-img">
          <img src="@/assets/image/user-head.png" alt="">
        </div>
        <p>上传头像</p>
      </div>
      <div class="user-head-right fl">
        <ul class="user-head-name fl">
          <li><b>{{ userAllAccountInfo.name }}</b></li>
          <li>{{ userAllAccountInfo.email }}</li>
          <li>最近登录：{{ userAllAccountInfo.lastLoginTime }}</li>
        </ul>
        <div class="user-head-money fr">
          <p>可用余额：<span>￥{{ userAllAccountInfo.availableMoney }}元</span></p>
          <a href="user_pay.html" target="_blank" class="user-head-a1">充值</a>
          <a href="details.html" target="_blank" class="user-head-a2">投资</a>
        </div>
      </div>

    </div>
    <!--记录-->
    <div class="user-record-box clearfix">
      <RecodeView :recode="bidInfosRecords" :sign="1"></RecodeView>
      <RecodeView :recode="records" :sign="2"></RecodeView>
      <RecodeView :recode="incomeRecords" :sign="3"></RecodeView>
<!--            <div class="user-record user-record-1">
              <h3 class="user-record-title">最近投资</h3>
              <table align="center" width="388" border="0" cellspacing="0" cellpadding="0">
                <thead class="datail_thead">
                <tr>
                  <th>序号</th>
                  <th>投资产品</th>
                  <th>投资金额</th>
                  <th>投资时间</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>1</td>
                  <td>新手宝</td>
                  <td>1500.0</td>
                  <td>2021-08-19</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>新手宝</td>
                  <td>1500.0</td>
                  <td>2021-08-19</td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>新手宝</td>
                  <td>1500.0</td>
                  <td>2021-08-19</td>
                </tr>
                <tr>
                  <td>4</td>
                  <td>新手宝</td>
                  <td>1500.0</td>
                  <td>2021-08-19</td>
                </tr>
                <tr>
                  <td>5</td>
                  <td>新手宝</td>
                  <td>1500.0</td>
                  <td>2021-08-19</td>
                </tr>
                <tr>
                  <td>6</td>
                  <td>新手宝</td>
                  <td>1500.0</td>
                  <td>2021-08-19</td>
                </tr>
                </tbody>
              </table>
              &lt;!&ndash;无记录&ndash;&gt;
              <p class="user-record-no">还没有投资记录，请投资：<a href="user_center.html" target="_blank">投资</a></p>
            </div>
            <div class="user-record user-record-2">
              <h3 class="user-record-title">最近充值</h3>
              <table align="center" width="388" border="0" cellspacing="0" cellpadding="0">
                <thead class="datail_thead">
                <tr>
                  <th>序号</th>
                  <th>充值描述</th>
                  <th>充值金额</th>
                  <th>充值时间</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>1</td>
                  <td>新手宝</td>
                  <td>1500.0</td>
                  <td>2021-08-19</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>新手宝</td>
                  <td>1500.0</td>
                  <td>2021-08-19</td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>新手宝</td>
                  <td>1500.0</td>
                  <td>2021-08-19</td>
                </tr>
                <tr>
                  <td>4</td>
                  <td>新手宝</td>
                  <td>1500.0</td>
                  <td>2021-08-19</td>
                </tr>
                <tr>
                  <td>5</td>
                  <td>新手宝</td>
                  <td>1500.0</td>
                  <td>2021-08-19</td>
                </tr>
                <tr>
                  <td>6</td>
                  <td>新手宝</td>
                  <td>1500.0</td>
                  <td>2021-08-19</td>
                </tr>
                </tbody>
              </table>
              &lt;!&ndash;无记录&ndash;&gt;
              <p class="user-record-no">还没有充值记录，请充值：<a href="user_pay.html" target="_blank">充值</a></p>
            </div>
            <div class="user-record user-record-3">
              <h3 class="user-record-title ">最近收益</h3>
              <table align="center" width="388" border="0" cellspacing="0" cellpadding="0">
                <thead class="datail_thead">
                <tr>
                  <th>序号</th>
                  <th>项目名称</th>
                  <th>投资日期</th>
                  <th>收益金额</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>1</td>
                  <td>新手宝</td>
                  <td>2021-08-19</td>
                  <td>0.46</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>新手宝</td>
                  <td>2021-08-19</td>
                  <td>0.46</td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>新手宝</td>
                  <td>2021-08-19</td>
                  <td>0.46</td>
                </tr>
                <tr>
                  <td>4</td>
                  <td>新手宝</td>
                  <td>2021-08-19</td>
                  <td>0.46</td>
                </tr>
                <tr>
                  <td>5</td>
                  <td>新手宝</td>
                  <td>2021-08-19</td>
                  <td>0.46</td>
                </tr>
                <tr>
                  <td>6</td>
                  <td>新手宝</td>
                  <td>2021-08-19</td>
                  <td>0.46</td>
                </tr>
                </tbody>
              </table>
              &lt;!&ndash;无记录&ndash;&gt;
              <p class="user-record-no">还没有收益记录</p>
            </div>-->
    </div>


  </div>
  <FooterView></FooterView>
</template>
<script setup lang="ts">
import RecodeView from './recode/RecodeView.vue'
import HttpUtil from "@/api";
import {RecodeType, UserAllAccountInfoType} from "@/interface/typeInterface"
import {onMounted, ref} from "vue";
import {useRouter, useRoute} from "vue-router";
// import qs from 'qs'

const router = useRouter()
const route = useRoute()
const userAllAccountInfo = ref<UserAllAccountInfoType>({
  "id": 0,
  "phone": "",
  "email": "",
  "name": "",
  "lastLoginTime": "",
  "headerImage": "",
  "availableMoney": 0
})
const records = ref<RecodeType[]>([{
  "id": 0,
  "result": "",
  "rechargeDate": "",
  "rechargeMoney": 0
}])
const bidInfosRecords = ref<RecodeType[]>([{
  "id": 0,
  "result": "",
  "rechargeDate": "",
  "rechargeMoney": 0
}])
const incomeRecords = ref<RecodeType[]>([{
  "id": 0,
  "result": "",
  "rechargeDate": "",
  "rechargeMoney": 0
}])

onMounted(() => {
  HttpUtil.get(`/v1/user/UserAllAccountInfo?uid=${route.query.uid}`).then(value => {
    if (value.data.code == 1000) {
      userAllAccountInfo.value = value.data.data
    }
  })
  HttpUtil.get(`/v1/recharge/records?uid=${route.query.uid}`).then(value => {
    if (value.data.code == 1000){
      records.value = value.data.data.records
      bidInfosRecords.value = value.data.data.bidInfosRecords
      incomeRecords.value = value.data.data.incomeRecords

    }
  })
})

</script>


<style scoped>

</style>