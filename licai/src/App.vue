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
console.log(import.meta.env)
</script>


<style scoped>

</style>
