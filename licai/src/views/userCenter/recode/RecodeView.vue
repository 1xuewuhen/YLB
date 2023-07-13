<template>
  <div class="user-record user-record-1">
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
      <tr v-for="(item,index) in defaults.recode" :key="item.id">
        <td>{{ ++index }}</td>
        <td>{{ item.result }}</td>
        <td>{{ item.rechargeMoney }}</td>
        <td>{{ item.rechargeDate }}</td>
      </tr>
      </tbody>
    </table>
    <!--无记录-->
    <p class="user-record-no">还没有投资记录，请投资：<a href="user_center.html" target="_blank">投资</a></p>
  </div>
</template>

<script setup lang="ts">
import type {RecodeType} from "@/interface/typeInterface";

var defaults = withDefaults(defineProps<{
  recode: RecodeType[]
}>(), {
  recode: [
    {
      "id": 0,
      "result": "",
      "rechargeDate": "",
      "rechargeMoney": 0
    }
  ]
});
console.log(defaults.recode)
</script>

<style scoped>

</style>