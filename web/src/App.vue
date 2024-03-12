<template>
  <div id="app">
    <router-view></router-view>
  </div>

</template>

<style>
body {
  background-image: url("assets/photo2.webp");
  background-size: cover;
  background-attachment: fixed;
}

#app {
  position: relative;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /*text-align: center;*/
  font-family: serif;
  background-color: rgba(255, 255, 255, 0.1);

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
  /*width: 1200px;*/
  /*background-color: #42b983;*/
}

</style>
