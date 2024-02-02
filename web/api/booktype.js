import http from "@/utils/request";

export default {
    async getBookType(params) {
        return await http.post("frontApi/bookType/type", params);
    },
}