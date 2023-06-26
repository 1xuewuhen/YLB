<template>
  <HeaderView></HeaderView>
  <div>
    {{ route.query.pType }}
  </div>

  <FooterView></FooterView>
</template>

<script setup lang="ts">
import {useRoute} from 'vue-router';

const route = useRoute()
</script>

<style scoped>

</style>