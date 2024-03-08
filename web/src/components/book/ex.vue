<template>
  <div id="app">
    <div v-for="item in items">
      <p v-if="item.show">{{ item.name }}</p>
      <button @click="toggleItem(item)">Toggle</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {id: 1, name: 'Item 1', show: true},
        {id: 2, name: 'Item 2', show: false},
        {id: 3, name: 'Item 3', show: true}
      ]
    };
  },
  methods: {
    toggleItem(item) {
      item.show = !item.show;
    }
  }
};
</script>

<style scoped>


</style>