<template>
  <div id="app">
    <router-view></router-view>
  </div>

</template>

<style>

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

  /*text-align: center;*/
  color: #2c3e50;
  font-family: serif;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

body {
  margin: auto;
  width: 1200px;
  /*background-color: #42b983;*/
}

</style>
