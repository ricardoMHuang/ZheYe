<template>
  <div>
    <el-row>
      <el-col :span="6" v-for="(group,j) in groupList" :key="j" style="margin-bottom: 45px">
        <div :body-style="{ padding: '0px'} " style="display: flex">
          <el-card :body-style="{padding: 0}" style="width: 78px;height: 78px; position: relative">
            <img class="group-image" :src="group.image" @click="openGroup(group.id)">
          </el-card>
          <div style="margin:5px 0 0 10px;text-align: left">
            <div class="post-title">{{ group.name }}</div>
            <div style="font-size: 15px; height:26px;color: #8c939d">{{ group.number }}个成员</div>
            <div>
              <button class="exit-button">
                <span class="shadow"></span>
                <span class="edge"></span>
                <span class="front text" @click="clear(group.id)">退出</span>
              </button>
            </div>
          </div>
        </div>

      </el-col>
    </el-row>

  </div>
</template>

<script>
import groupCollectApi from "../../../../../api/groupCollect";
import groupApi from "../../../../../api/group";

export default {
  name: "myGroup",

  data() {
    return {
      groupList: [],

    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.getGroups();
    },
    async getGroups() {
      let res = await groupApi.getUJGroupList(JSON.parse(window.sessionStorage.getItem("userInfo")).id)

      res = res.data;
      console.log(res.message, res.data);
      this.groupList = res.data;
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
    //退出小组
    async quitGroup(groupId) {
      let isSuccess = await groupCollectApi.deleteGroupCollect({
        "groupId": groupId,
        "userId": JSON.parse(window.sessionStorage.getItem("userInfo")).id,
      });
      await groupApi.reduceNumber(groupId);
      isSuccess = isSuccess.data;
      console.log(isSuccess.message);
      await this.getGroups();
    },
    openGroup(id) {
      this.$router.push({
        path: '/group/groupDetail',
        query: {
          groupId: id,
        }
      })
    },

  },
}
</script>

<style scoped>
.group-image {
  cursor: pointer;
  width: 78px;
  height: 78px;
  transition: 0.8s;
}

.group-image:hover {
  transform: scale(0.8);
}

.exit-button {
  position: relative;
  border: none;
  background: transparent;
  padding: 0;
  cursor: pointer;
  outline-offset: 4px;
  transition: filter 250ms;
  user-select: none;
  touch-action: manipulation;
}

.shadow {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border-radius: 5px;
  background: hsl(0deg 0% 0% / 0.25);
  will-change: transform;
  transform: translateY(2px);
  transition: transform 600ms cubic-bezier(.3, .7, .4, 1);
}

.edge {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border-radius: 5px;
  background: linear-gradient(
      to left,
      hsl(340deg 100% 16%) 0%,
      hsl(340deg 100% 32%) 8%,
      hsl(340deg 100% 32%) 92%,
      hsl(340deg 100% 16%) 100%
  );
}

.front {
  display: block;
  position: relative;
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 0.8rem;
  color: white;
  background: hsl(345deg 100% 47%);
  will-change: transform;
  transform: translateY(-4px);
  transition: transform 600ms cubic-bezier(.3, .7, .4, 1);
}

.exit-button:hover {
  filter: brightness(110%);
}

.exit-button:hover .front {
  transform: translateY(-6px);
  transition: transform 250ms cubic-bezier(.3, .7, .4, 1.5);
}

.exit-button:active .front {
  transform: translateY(-2px);
  transition: transform 34ms;
}

.exit-button:hover .shadow {
  transform: translateY(4px);
  transition: transform 250ms cubic-bezier(.3, .7, .4, 1.5);
}

.exit-button:active .shadow {
  transform: translateY(1px);
  transition: transform 34ms;
}

.exit-button:focus:not(:focus-visible) {
  outline: none;
}

.el-card {
  box-shadow: 15px 15px 30px #bebebe,
  -15px -15px 30px #ffffff;
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