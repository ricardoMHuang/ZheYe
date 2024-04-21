import http from "@/utils/request";

export default {
    async getGroupListByClass(typeId) {
        return await http.post("frontApi/group/getGroupListByClass", typeId);

    },
    async getUJGroupList(userId) {
        return await http.post("frontApi/group/getUJGroupList", userId);

    },
    async getUCGroupList(userId) {
        return await http.post("frontApi/group/getUCGroupList", userId);

    },
    async getGroup(groupId) {
        return await http.post("frontApi/group/getGroup", groupId);
    },
    async disbandGroup(group) {
        return await http.post("frontApi/group/disbandGroup", group);
    },

    async addNumber(groupId) {
        return await http.post("frontApi/group/addNumber", groupId);
    },
    async reduceNumber(groupId) {
        return await http.post("frontApi/group/reduceNumber", groupId);
    },
    async create(group) {
        return await http.post("frontApi/group/create", group);
    },
    async deleteImage(filepath) {
        return await http.post("frontApi/group/deleteImage", filepath);
    },
}