<template>
  <router-view></router-view>
</template>
<script setup lang="ts">
import {PlatInfoStore} from '@/stores'
import {onMounted} from "vue";

const platInfoStore = PlatInfoStore()
onMounted(async () => {
  await platInfoStore.getPlatInfo()
})
</script>


<style scoped>

</style>
