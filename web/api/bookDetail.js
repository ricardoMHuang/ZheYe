import http from "@/utils/request";

export default {
    async submitComment(params) {
        return await http.post("frontApi/bookDetail/submitComment", params);
    },
    async getComment(params) {
        return await http.post("frontApi/bookDetail/getComment", params);
    },
}