<template>
  <div class="myart1">
    <article-item/>
  </div>
</template>

<script>
/*import { myArticle } from "@/api/user.js";
*/
import ArticleItem from "@/components/personalCenter/follow/ArticleItem";

export default {
  components: {ArticleItem},
  name: "MyArticle",
  data() {
    return {
      allData: [],

    };
  },

  mounted() {
    this.load();
  },
  methods: {

    async load() {
      await myArticle(this.$route.params.id)
          .then((res) => {
            console.log(res);
            this.allData = res.data
          })
          .catch((err) => {
            console.log(err);
          });
    },
  },
};
</script>

<style>
.myart1 {
  line-height: 30px;
}
</style>
