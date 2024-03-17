import http from "@/utils/request";

export default {

    insertGroupCollect(groupCollect) {
        return http.post("frontApi/groupCollect/insertGroupCollect", groupCollect);
    },

    selectGroup(groupCollect) {
        return http.post("frontApi/groupCollect/selectGroup", groupCollect);
    },
    selectGroupByUserId(userId) {
        return http.post("frontApi/groupCollect/selectGroupByUserId", userId);
    },
    deleteGroupCollect(groupCollect) {
        return http.post("frontApi/groupCollect/deleteGroupCollect", groupCollect);

    },
    selectByGroupId(groupId) {
        return http.post("frontApi/groupCollect/selectByGroupId", groupId);

    }
}