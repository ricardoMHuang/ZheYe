import http from "@/utils/request";
import user from "./user";

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
    //根据BookComment表的主键id查找，返回一条评论
    async getCommentById(id) {
        return await http.post("frontApi/bookDetail/getCommentById", id);
    },
    //根据BookComment表的userId查找，返回一条或多条
    async getCommentByUserId(userId) {
        return await http.post("frontApi/bookDetail/getCommentByUserId", userId);

    },
    //根据BookComment表的commentId查找，返回一条或多条
    selectCommentByCommentId(commentId) {
        return http.post("frontApi/bookDetail/selectCommentByCommentId", commentId);
    }
}