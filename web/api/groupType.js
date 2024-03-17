import http from "@/utils/request";

export default {
    async getAllType() {
        return await http.post("frontApi/group-type/getAllTypeItem");
    },
    getGroupType(typeId) {
        return http.post("frontApi/group-type/getGroupType", typeId);
    }
}