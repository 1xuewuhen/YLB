<template>
  <div ref="styleDiv"
  >
    <h3 class="user-record-title" v-if="sign==1">最近投资</h3>
    <h3 class="user-record-title" v-else-if="sign==2">最近充值</h3>
    <h3 class="user-record-title" v-else>最近收益</h3>
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
    <p class="user-record-no">还没有投资记录，请投资：<router-link to="/index" >投资</router-link></p>
  </div>
</template>

<script setup lang="ts">
import type {RecodeType} from "@/interface/typeInterface";
import {onMounted, ref} from "vue";

const styleDiv = ref()

const defaults = withDefaults(defineProps<{
  sign: number
  recode: RecodeType[]
}>(), {
  sign: 0,
  recode: [
    {
      "id": 0,
      "result": "",
      "rechargeDate": "",
      "rechargeMoney": 0
    }
  ],
});
onMounted(() => {
  switchStyle(styleDiv.value)
})
const switchStyle = (element: HTMLDivElement) => {
  switch (defaults.sign) {
    case 1: {
      element.setAttribute('class','user-record user-record-1')
      break
    }
    case 2: {
      element.setAttribute('class','user-record user-record-2')
      break
    }
    case 3: {
      element.setAttribute('class','user-record user-record-3')
      break
    }
  }
}
</script>

<style scoped>

</style>