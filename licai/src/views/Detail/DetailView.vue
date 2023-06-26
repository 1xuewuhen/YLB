<template>
  <div>
    产品详情

    {{ route.query.productId }}

  </div>
</template>

<script setup lang="ts">
import {useRoute} from "vue-router";

const route = useRoute()
</script>

<style scoped>

</style>