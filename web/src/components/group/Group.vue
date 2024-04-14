<template>
  <div style="margin:auto;padding: 50px 0; min-height: 1000px">
    <!--头部-->
    <div>
      <p style="font-size:20px; font-weight: bold;text-align: center">
        加入一些小组来定制你的小组首页。我们为你推荐了一些，按分类选择你感兴趣的加入，试试看！</p>
    </div>
    <!--  分类-->
    <div>
      <div>
        <el-tabs type="card" style="display: block; background-color: #fcfbf9;">
          <el-tab-pane v-for="(item,i) in classes" :key="i" style="margin-top: 50px">
            <li slot="label" style="width: 100px;margin:0 0 0 10px">
              <h3 style="font-size: 20px;margin: 0">{{ item.name }}</h3>
              <div style="font-size: 10px;height:40px;overflow: hidden;text-overflow:ellipsis;">
                {{ item.description }}
              </div>
            </li>
            <el-empty v-if="item.groupList.length===0" description="该类别还没有小组呢，快去创建吧！"></el-empty>
            <el-row v-else>
              <el-col :span="6" v-for="(group,j) in item.groupList" :key="j" :offset="1" style="margin-bottom: 45px">
                <div :body-style="{ padding: '0px'} " style="display: flex">
                  <el-card :body-style="{padding: 0}" style="width: 85px; position: relative">
                    <img class="group-image" :src="group.image" @click="openGroup(group.id)">
                  </el-card>
                  <div style="margin:5px 0 0 10px">
                    <div class="post-title">{{ group.name }}</div>
                    <div style="font-size: 15px; height:26px;color: #8c939d">{{ group.number }}个成员</div>
                    <div>
                      <el-button v-if="group.btnStatus" type="text" class="el_link" style="color:#356309"
                                 @click="clear(group.id)">已加入
                      </el-button>
                      <el-button v-else type="text" class="el_link" style="color:#CA7158" @click="open(group.id)">+加入小组
                      </el-button>
                    </div>
                  </div>
                </div>

              </el-col>
            </el-row>

          </el-tab-pane>
          <!--        分页-->
          <div class="block" style="display: flex;justify-content: center">
            <el-pagination
                layout="prev, pager, next"
                :total="1000">
            </el-pagination>
          </div>
          <div style="display: flex;justify-content: center;padding: 20px 0;background-color: rgba(255,255,255)">
            <span style="margin: 10px 10px 0 10px;">已加入{{ this.myGroupSum }}个小组</span>
            <el-button type="primary" style="background-color: rgb(110,191,195);color: white" plain
                       @click="openMyGroups()">看看我的小组
            </el-button>
          </div>
        </el-tabs>

      </div>
    </div>
  </div>
</template>

<script>
import groupCollectApi from "../../../api/groupCollect"
import groupApi from "../../../api/group"
import groupTypeApi from "../../../api/groupType"

export default {

  name: "Group_NewUser",

  data() {
    return {
      userId: JSON.parse(window.sessionStorage.getItem("userInfo")).id,
      activeName: 'second',
      currentDate: new Date(),
      input: '',
      myGroupSum: 0,
      classes: [],
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      await this.getClasses();
      await this.getMyGroupSum();
    },
    //按分类获取的各类小组的集合:classes
    async getClasses() {
      let allGroupType = await groupTypeApi.getAllType();
      allGroupType = allGroupType.data;
      if (allGroupType.code === 200) {
        this.classes = allGroupType.data;
        for (const class1 of this.classes) {
          let groupList = await groupApi.getGroupListByClass(class1.id);
          groupList = groupList.data;
          if (groupList.code === 200) {
            this.$set(class1, "groupList", groupList.data)
          }
        }
      }
      await this.addBtnStatus();
      console.log(allGroupType.message);
      console.log(this.classes);
    },
    //在classes中加入按钮状态
    async addBtnStatus() {
      for (let item of this.classes) {
        for (const itemElement of item.groupList) {
          let res = await groupCollectApi.selectGroup({
            "groupId": itemElement.id,
            "userId": this.userId,
          });
          res = res.data;
          if (res.code === 200) {
            this.$set(itemElement, 'btnStatus', true);
          } else {
            this.$set(itemElement, 'btnStatus', false);
          }
        }
      }
    },
    //查看小组详情
    openGroup(id) {
      this.$router.push({
        path: '/group/groupDetail',
        query: {
          groupId: id
        }
      })
    },
    //确认加入小组的弹框
    open(id) {
      this.$confirm('确认加入小组?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.joinGroup(id);
        this.$message({
          type: 'success',
          message: '成功加入!',

        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    //确认退出小组的弹框
    clear(id) {
      this.$confirm('确认退出小组?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.quitGroup(id);
        this.$message({
          type: 'success',
          message: '成功退出!',

        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    //加入小组
    async joinGroup(groupId) {
      let dt = new Date();
      let year = dt.getFullYear();
      let month = dt.getMonth() + 1;
      if (month < 10) {
        month = "0" + month;
      }
      let day = dt.getDate();
      if (day < 10) {
        day = "0" + day;
      }
      let time = year + "-" + month + "-" + day;
      let isSuccess = await groupCollectApi.insertGroupCollect({
        'groupId': groupId,
        'userId': this.userId,
        'collectTime': time,
      });
      await groupApi.addNumber(groupId);
      isSuccess = isSuccess.data;
      console.log(isSuccess.message);
      await this.getMyGroupSum();
      await this.getClasses();
    },
    //退出小组
    async quitGroup(groupId) {
      let isSuccess = await groupCollectApi.deleteGroupCollect({
        "groupId": groupId,
        "userId": this.userId,
      });
      await groupApi.reduceNumber(groupId);
      isSuccess = isSuccess.data;
      console.log(isSuccess.message);
      await this.getMyGroupSum();
      await this.getClasses();
    },
    //查询我加入的小组总数
    async getMyGroupSum() {
      let myGroup = await groupCollectApi.selectGroupByUserId(this.userId);
      myGroup = myGroup.data;
      this.myGroupSum = myGroup.data.length;
      console.log(myGroup.message);
      console.log(this.myGroupSum);
    },
    //  打开我的小组
    openMyGroups() {
      this.$router.push({
        path: '/group/myGroups'
      })
    },

  }
}
</script>

<style scoped>
/*类型属性*/
>>> .el-tabs__nav.is-top {
  width: 840px;
  height: 80px;
}

>>> .el-tabs__item.is-top {
  width: 120px;
  height: 80px;
  padding-left: 0;
  padding-right: 0;
  background-color: rgb(248, 248, 248);

}

>>> #tab-1 {
  padding-left: 0;
}

>>> .el-tabs__item.is-top.is-active {
  padding-left: 0;
  padding-right: 0;
}

>>> .el-tabs__header.is-top {
  width: 840px;
}

.fontClass {
  font-size: 25px;
}

.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.el_link {
  padding: 0;
  /*float: right;*/
}


.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.group-image {
  position: relative;
  display: block;
  cursor: pointer;
  width: 85px;
  height: 85px;
  transition: 1s;
}

.group-image::after {
  content: '';
  position: absolute;
  width: 100%;
  height: 100%;
  background-image: url("../../assets/photo2.webp");
  background-size: cover;
  top: 0;
  left: 0;
}

.group-image:hover {
  transform: scale(0.8);
}

.post-title {
  font-size: 18px;
  font-weight: bold;
  -webkit-line-clamp: 2;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>