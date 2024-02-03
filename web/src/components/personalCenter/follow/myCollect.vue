<template>
  <div class="div1" style="width: 100%;">
    <el-card class="box-card" v-for="(bookCollect,index) in allData" :key="bookCollect">
      <div slot="header" class="clearfix">
        <el-button style="float: right; padding: 3px 1px;" type="danger" icon="el-icon-delete"
                   @click="open(bookCollect,index)">删除
        </el-button>
      </div>
      <div class="text item">
        <el-card :body-style="{padding: 0}" style="width: 120px;height:170px; float: left;">
          <el-image :src="bookCollect.imageUrl" :lazy='true' style="width: 150px; float: left;height: 150px"/>
        </el-card>
        <div class="bookCoDetail">
          <h2>{{ bookCollect.bookName }}</h2>
          <span style="color: #99a9bf">{{ bookCollect.author }}    【{{ bookCollect.country }}】</span><br/>
          <p style="line-height: 18px; width: 860px">&nbsp;&nbsp;简介:{{ bookCollect.detail }}</p>
        </div>
      </div>
    </el-card>
    <el-empty
        :image="require('@/assets/空状态.png')"
        v-if="allData.length === 0"
        :image-size="250"
        description="暂未发表任何帖子耶"
    ></el-empty>
    <div class="pagination">
      <el-pagination
          background
          layout="total,prev,pager,next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="allData.length"
          @current-change="handleCurrentChange">
      </el-pagination>

    </div>
  </div>
</template>

<script>
import userApi from "@/../api/user";
import bookApi from "@/../api/book";
import ArticleItem from "@/components/personalCenter/follow/ArticleItem";
import bookCollectApi from "@/../api/bookCollect";


export default {
  components: {ArticleItem},
  name: "myCollect",
  data() {
    return {
      pageSize: 4,  //分页大小
      currentPage: 1,   //当前页
      allData: [{}, {}, {}]
    };
  },
  mounted() {
    this.load();
  },
  watch: {
    allData: {
      handler(oldVal, newVal) {

      }
    }
  },
  computed: {
    //计算当前搜索结果表里的数据
    data() {
      return this.artData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  methods: {
    /*获取当前页*/
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    /*删除收藏图书*/
    open(item, index) {
      this.$confirm('确定删除《' + item.bookName + '》吗', '删除', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }).then(() => {
        let userId = JSON.parse(window.sessionStorage.getItem('userInfo')).id;
        console.log(this.allData[index].id + " " + userId);
        let res = bookCollectApi.deleteBookCollection({bookId: this.allData[index].bookId, userId: userId});
        console.log(res);
        this.load();
        this.$message({
          type: 'success',
          message: '删除成功'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });

    },

    async load() {
      let userId = JSON.parse(window.sessionStorage.getItem('userInfo')).id;
      let res = await bookCollectApi.bookCollection({userId: userId});
      res = res.data;
      console.log(res);
      if (res.success)
        this.allData = res.data;

    },
  },
};
</script>

<style>
.mycol1 {
  line-height: 30px;
}

.text {
  font-size: 14px;
  padding-bottom: 10px;
}

.item {
  margin-bottom: 18px;

}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.div1 .box-card {
  margin-bottom: 10px;

}

.bookCoDetail {
  /*width: 200px;*/
  height: 200px;
  float: left;
  left: 20px;
  position: relative;
  /*font-family: 黑体,serif;*/
  /*font-weight: bold;*/
  text-align: left;
  margin: 10px;
}

.bookCoDetail span {
  line-height: 25px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
</style>
