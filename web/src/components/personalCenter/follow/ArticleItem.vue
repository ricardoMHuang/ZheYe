<template>
  <div style="padding: 5px;">
    <div v-for="item in data" class="container-block">

      <div style="text-align: left">
        <span style="font-size: 20px">{{ item.username }}</span>
        <span style="float: right">{{ item.data }}</span>
      </div>
      <div class="text-color">
        {{ item.content }}
      </div>
      <div style="float: right">——<a href="" style="color: #72aeae">{{ item.groupName }}</a>小组
      </div>
    </div>

    <el-empty
        :image="require('@/assets/空状态.png')"
        v-if="artData.length === 0"
        :image-size="250"
        description="暂未发表任何帖子耶"
    ></el-empty>
    <div class="pagination">
      <el-pagination
          background
          layout="total,prev,pager,next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="artData.length"
          @current-change="handleCurrentChange">
      </el-pagination>

    </div>
  </div>
</template>

<script>
import userApi from "../../../../api/user"

export default {
  name: "ArticleItem",
  data() {
    return {
      pageSize: 5,  //分页大小
      currentPage: 1,   //当前页
      artData: [
        {
          username: '江西网友',
          data: '1982-06-31',
          content: '据报道，光头强即将在2月中旬抵达狗熊岭。',
          groupName: '云淡风轻'
        },
        {
          username: '江西网友',
          data: '1982-06-31',
          content: '据报道，光头强即将在2月中旬抵达狗熊岭。',
          groupName: '云淡风轻'
        },
        {
          username: '江西网友',
          data: '1982-06-31',
          content: '据报道，光头强即将在2月中旬抵达狗熊岭。',
          groupName: '云淡风轻'
        },
        {
          username: '江西网友',
          data: '1982-06-31',
          content: '据报道，光头强即将在2月中旬抵达狗熊岭。',
          groupName: '云淡风轻'
        },
        {
          username: '江西网友',
          data: '1982-06-31',
          content: '据报道，光头强即将在2月中旬抵达狗熊岭。',
          groupName: '云淡风轻'
        },
        {
          username: '江西网友',
          data: '1982-06-31',
          content: '据报道，光头强即将在2月中旬抵达狗熊岭。',
          groupName: '云淡风轻'
        },
        {
          username: '江西网友',
          data: '1982-06-31',
          content: '据报道，光头强即将在2月中旬抵达狗熊岭。',
          groupName: '云淡风轻'
        }, {
          username: '江西网友',
          data: '1982-06-31',
          content: '据报道，光头强即将在2月中旬抵达狗熊岭。',
          groupName: '云淡风轻'
        },
        {
          username: '江西网友',
          data: '1982-06-31',
          content: '据报道，光头强即将在2月中旬抵达狗熊岭。',
          groupName: '云淡风轻'
        },
        {
          username: '江西网友',
          data: '1982-06-31',
          content: '据报道，光头强即将在2月中旬抵达狗熊岭。',
          groupName: '云淡风轻'
        },
        {
          username: '江西网友',
          data: '1982-06-31',
          content: '据报道，光头强即将在2月中旬抵达狗熊岭。',
          groupName: '云淡风轻'
        },

      ]
    };
  },
  computed: {
    //计算当前搜索结果表里的数据
    data() {
      return this.artData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  created() {
    this.load();
  },
  methods: {
    //获取当前页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    async load() {
      let res = await userApi.getUserArticle(window.sessionStorage.getItem("userInfo"));
      res = res.data;
      if (res.success) this.artData = res.data;
    }
  }
};
</script>
<style>
body {
  text-align: left;
}

.container-block {
  background-color: #f6f6f1;
  margin: 10px;
  height: 200px;
  padding: 10px 20px;
  border-radius: 6px;
}

.container-block:hover .inner-block:before,
.container-block:hover .slider-top-right:after {
  height: 100%;
}

.container-block:hover .inner-block:after,
.container-block:hover .slider-top-right:before {
  width: 100%;
}

.slider-top-right:before,
.inner-block:after {
  height: 4px;
  transition: width 0.75s ease;
  width: 0%;
}

.slider-top-right:after,
.inner-block:before {
  height: 0%;
  transition: height 0.75s ease;
  width: 4px;
}

.inner-block:before,
.inner-block:after,
.slider-top-right:before,
.slider-top-right:after {
  background-color: #f1ecdc;
  content: "";
  display: block;
  position: absolute;
}

.inner-block {
  font-size: 2em;
  width: 90%;
  height: 90%;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
}

.inner-block:before {
  bottom: 0;
  left: 0;
}

.inner-block:after {
  bottom: 0;
  right: 0;
}

.slider-top-right {
  position: relative;
  width: 100%;
  height: 100%;
}

.slider-top-right:before {
  top: 0;
  left: 0;
}

.slider-top-right:after {
  top: 0;
  right: 0;
}

.text-color {
  text-align: left;
  padding: 10px;
  height: 100px;
}

</style>