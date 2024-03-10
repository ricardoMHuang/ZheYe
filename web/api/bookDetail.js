import http from "@/utils/request";

export default {
    async submitComment(params) {
        return await http.post("frontApi/bookDetail/submitComment", params);
    },
    async getComment(params) {
        return await http.post("frontApi/bookDetail/getComment", params);
    },
    async updateComment(comment) {
        return await http.post("frontApi/bookDetail/updateComment", comment);

    },
    async getCommentById(id) {
        return await http.post("frontApi/bookDetail/getCommentById", id);
    }
}