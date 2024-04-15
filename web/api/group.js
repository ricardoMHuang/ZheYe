import http from "@/utils/request";

export default {
    async getGroupListByClass(typeId) {
        return await http.post("frontApi/group/getGroupListByClass", typeId);

    },
    async getUJGroupList(userId) {
        return await http.post("frontApi/group/getUJGroupList", userId);

    },
    async getGroup(groupId) {
        return await http.post("frontApi/group/getGroup", groupId);
    },
    async addNumber(groupId) {
        return await http.post("frontApi/group/addNumber", groupId);
    },
    async reduceNumber(groupId) {
        return await http.post("frontApi/group/reduceNumber", groupId);
    },
}