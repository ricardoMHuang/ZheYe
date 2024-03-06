<template>
  <div style="width: 80%;text-align: center;margin: auto;">
    <!--返回上一页-->
    <el-page-header @back="goBack" content="分类榜单" style="padding: 20px"></el-page-header>

    <el-tabs :tab-position="tabPosition">
      <el-tab-pane :label="o.type" v-for="(o,index) in typeList">
        <h2 style="margin-bottom: 80px">{{ o.type }}</h2>
        <div v-for="(l,i) in o.list" style="height: 200px;margin-bottom: 10px">
          <h3 class="rank">{{ i + 1 }}</h3>
          <el-card :body-style="{padding:0}" style="float: left;width: 12%;margin-left: 20px">
            <img :src="l.url" class="image">
          </el-card>
          <div class="wenBen">
            <h3 style="margin-top: 0">{{ l.bookName }}</h3>
            <h4 style="color: #999999;">[{{ l.country }}] {{ l.author }}</h4>
            <p class="textOverflow" @click="openArticle">
              {{ l.detail }}
            </p>
            <el-divider></el-divider>
          </div>
        </div>
        <el-pagination
            background
            layout="prev, pager, next"
            :total="1000">
        </el-pagination>
      </el-tab-pane>
    </el-tabs>

  </div>


</template>

<script>
import bookApi from "@/../api/book"

export default {
  name: "category",
  data() {
    return {
      count: 0,
      tabPosition: 'left',
      typeList: [
        {
          type: "文学",
          list: [
            {
              id: 1,
              type: "文学",
              url: "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fci.xiaohongshu.com%2F81ffb8e9-dda3-88ae-c67e-298be8f7584a%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fci.xiaohongshu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672664302&t=5f05e8b359f51b461580872d493bd62c",
              bookName: "我的辽阔天地",
              country: "法",
              author: "卡特琳·默里",
              detail: "本书用活泼的语言、漫画的形式，结合历史故事，使读者从整体上可以快速了解整个欧洲建筑的发展历史，包括西方建筑的老祖宗为什么在古希腊，罗马角斗场是否是帝国的维稳工具，哥特式大教堂怎么就成了黑暗化身，等等。本书适合欧洲建筑爱好者、初学者阅读，也适合喜欢建筑历史文化的大众读者阅读。\n"
            },
            {
              id: 1,
              type: "文学",
              url: "https://img1.baidu.com/it/u=590709266,2477320825&fm=253&fmt=auto&app=120&f=JPEG?w=372&h=333",
              bookName: "我的辽阔天地",
              country: "法",
              author: "卡特琳·默里"
            },
            {
              id: 1,
              type: "文学",
              url: "https://img1.baidu.com/it/u=590709266,2477320825&fm=253&fmt=auto&app=120&f=JPEG?w=372&h=333",
              bookName: "我的辽阔天地",
              country: "法",
              author: "卡特琳·默里"
            },
            {
              id: 1,
              url: "https://img1.baidu.com/it/u=590709266,2477320825&fm=253&fmt=auto&app=120&f=JPEG?w=372&h=333",
              bookName: "我的辽阔天地",
              country: "法",
              author: "卡特琳·默里"
            },
          ]
        },
        {
          type: "小说",
          list: [
            {
              id: 1,
              type: "文学",
              url: "https://img1.baidu.com/it/u=590709266,2477320825&fm=253&fmt=auto&app=120&f=JPEG?w=372&h=333",
              bookName: "我的辽阔天地",
              country: "法",
              author: "卡特琳·默里"
            },
            {
              id: 1,
              url: "https://img1.baidu.com/it/u=590709266,2477320825&fm=253&fmt=auto&app=120&f=JPEG?w=372&h=333",
              bookName: "我的辽阔天地",
              country: "法",
              author: "卡特琳·默里"
            },
          ]
        },
        {
          type: "散文",
          list: [
            {
              id: 1,
              url: "https://img1.baidu.com/it/u=590709266,2477320825&fm=253&fmt=auto&app=120&f=JPEG?w=372&h=333",
              bookName: "我的辽阔天地",
              country: "法",
              author: "卡特琳·默里"
            },
            {
              id: 1,
              url: "https://img1.baidu.com/it/u=590709266,2477320825&fm=253&fmt=auto&app=120&f=JPEG?w=372&h=333",
              bookName: "我的辽阔天地",
              country: "法",
              author: "卡特琳·默里"
            },
          ]
        },
        {
          type: "散文",
          list: [
            {
              id: 1,
              url: "https://img1.baidu.com/it/u=590709266,2477320825&fm=253&fmt=auto&app=120&f=JPEG?w=372&h=333",
              bookName: "我的辽阔天地",
              country: "法",
              author: "卡特琳·默里"
            },
            {
              id: 1,
              url: "https://img1.baidu.com/it/u=590709266,2477320825&fm=253&fmt=auto&app=120&f=JPEG?w=372&h=333",
              bookName: "我的辽阔天地",
              country: "法",
              author: "卡特琳·默里"
            },
          ]
        },
        {
          type: "散文",
          list: [
            {
              id: 1,
              url: "https://img1.baidu.com/it/u=590709266,2477320825&fm=253&fmt=auto&app=120&f=JPEG?w=372&h=333",
              bookName: "我的辽阔天地",
              country: "法",
              author: "卡特琳·默里"
            },
            {
              id: 1,
              url: "https://img1.baidu.com/it/u=590709266,2477320825&fm=253&fmt=auto&app=120&f=JPEG?w=372&h=333",
              bookName: "我的辽阔天地",
              country: "法",
              author: "卡特琳·默里"
            },
          ]
        },
      ]
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    /*页面初始化*/
    async init() {
      /*  let res=await bookApi.getBookBytype(this.$route.query.id);*/
      let typeList = JSON.parse(this.$route.query.typeList);

      for (let i = 0; i < typeList.length; i++) {
        this.typeList[i].type = typeList[i];

      }
    },
    //返回上一级
    goBack() {
      this.$router.back();
    },
    openArticle() {
      this.$router.push({
        path: '/book/article',
        query: {
          id: 1,
        }
      })
    },
    load() {
      this.count += 2
    },
  }
}
</script>

<style scoped>
/deep/ .el-tabs {
  height: 1200px;
  /*background-color: red;*/
}

.image {
  width: 100%;
  display: block;
  cursor: pointer;
}

.textOverflow {
  -webkit-line-clamp: 3;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
  cursor: pointer;
}

.rank {
  width: 8%;
  float: left;
  margin-top: 70px;
  font-size: 25px;
  font-style: italic;
}

.wenBen {
  float: left;
  text-align: left;
  width: 70%;
  margin-left: 50px;
}
</style>