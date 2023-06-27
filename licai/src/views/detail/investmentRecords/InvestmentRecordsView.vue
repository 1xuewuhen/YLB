<template>
  <!--投资记录-->
  <div class="datail-record">
    <h2 class="datail-record-title">投资记录</h2>
    <div class="datail-record-list">
      <table align="center" width="880" border="0" cellspacing="0" cellpadding="0">
        <colgroup>
          <col style="width: 72px"/>
          <col style="width: 203px"/>
          <col style="width: 251px"/>
          <col style="width: 354px"/>
        </colgroup>
        <thead class="datail_thead">
        <tr>
          <th>序号</th>
          <th>投资人</th>
          <th>投资金额（元）</th>
          <th>投资时间</th>
        </tr>
        </thead>
        <tbody v-for="(item,index) in bidInfoList" :key="item.id">
        <tr>
          <td>{{ index + 1 }}</td>
          <td class="datail-record-phone">{{ item.phone }}</td>
          <td>{{ item.bidMoney }}</td>
          <td>{{ item.bidTime }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script setup lang="ts">
import {BidInfoListType} from '@/interface/typeInterface'

const defaults = withDefaults(defineProps<{
  bidInfoList: BidInfoListType[]
}>(), {
  bidInfoList: [{
    "id": 3227,
    "phone": "135******05",
    "bidTime": "2020-08-20 15:09:52",
    "bidMoney": 100
  }]
});
</script>

<style scoped>

</style>