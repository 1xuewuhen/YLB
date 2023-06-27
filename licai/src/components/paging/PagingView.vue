<template>
  <!--分页-->
  <div class="page_box">
    <ul class="pagination">
      <li @click="setPageNo(1)"><a href="javascript:void (0)">首页</a></li>
      <li @click="setPageNo(--page.pageNo)"><a href="javascript:void (0)">上一页</a></li>
      <li class="active"><span>{{ page.pageNo }}</span></li>
      <li @click="setPageNo(++page.pageNo)"><a href="javascript:void (0)">下一页</a></li>
      <li @click="setPageNo(page.totalPage)"><a href="javascript:void (0)">尾页</a></li>
      <li class="totalPages"><span>共{{ page.totalPage }}页</span></li>
    </ul>
  </div>
</template>
<script setup lang="ts">
import {PageType} from "@/interface/typeInterface";
import layx from "vue-layx"

const defaults = withDefaults(defineProps<{
  page: PageType
}>(), {
  page: {
    pageNo: 1,
    pageSize: 0,
    totalPage: 0,
    totalRecord: 0
  }
});
const emit = defineEmits<{
  (e: "on-click", pageNo: number): void
}>()
const setPageNo = (pageNo: number) => {
  if (pageNo < 1) {
    defaults.page.pageNo = 1
    layx.msg('页码不能为0', {dialogIcon: 'warn'})
  } else if (pageNo > defaults.page.totalPage) {
    defaults.page.pageNo = defaults.page.totalPage
    layx.msg('页码不能超过总页数', {dialogIcon: 'warn'})
  } else {
    emit('on-click', pageNo)
  }
}

</script>


<style scoped>

</style>