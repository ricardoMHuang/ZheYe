<template>
  <div>
    <article-item/>
  </div>
</template>

<script>
/*import { myArticle } from "@/api/user.js";
*/
import ArticleItem from "@/components/personalCenter/follow/ArticleItem";

export default {
  components: {ArticleItem},
  name: "MyMessage",
  data() {
    return {};
  },

  mounted() {
    this.load();
  },
  methods: {

  },
};
</script>

<style>

</style>
