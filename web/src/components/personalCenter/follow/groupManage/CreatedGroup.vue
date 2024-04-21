<template>
  <div>
    <el-row>
      <el-empty v-if="ICrtGroupList.length===0" description="你还未创建过小组诶"></el-empty>
      <el-col v-else :span="6" v-for="(group,j) in ICrtGroupList" :key="j" style="margin-bottom: 45px">
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
                <span class="front text" @click="disband(group.id)">解散</span>
              </button>
            </div>
          </div>
        </div>
      </el-col>

    </el-row>
    <el-button @click="createGroup">创建小组</el-button>
  </div>
</template>

<script>
import groupApi from "../../../../../api/group";
import groupCollectApi from "../../../../../api/groupCollect";

export default {
  name: "CreateGroup",
  data() {
    return {
      groupEmptyVisible: true,
      ICrtGroupList: [],
      rule: [],
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.getCreatedGroups();
    },
    async getCreatedGroups() {
      let res = await groupApi.getUCGroupList(JSON.parse(window.sessionStorage.getItem("userInfo")).id);
      res = res.data;
      if (res.code === 200) {
        this.groupEmptyVisible = false
      }
      console.log(res.message, res.data);
      this.ICrtGroupList = res.data;
    },
    //确认解散小组的弹框
    disband(groupId) {
      this.$prompt('请输入解散理由', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^.+$/,
        inputErrorMessage: "解散理由不能为空",
        type: 'warning'
      }).then(({value}) => {
        this.disbandGroup(groupId, value);
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
    //解散小组
    async disbandGroup(groupId, disbandReason) {
      let res = await groupApi.disbandGroup({id: groupId, disbandReason: disbandReason});
      res = res.data;
      console.log(res.message, res.data);
      await this.getCreatedGroups();
    },
    createGroup() {
      this.$router.push({
        path: "/groupEditor",
      })
    },
    openGroup(groupId) {
      this.$router.push({
        path: "/group/groupDetail",
        query: {
          groupId: groupId,
        },
      })

    },
  }


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