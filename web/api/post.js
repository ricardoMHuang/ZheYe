import http from "@/utils/request";


export default {
    async getHotPostList(userId) {
        return await http.post("frontApi/post/getHotPostList", userId);
    },
    async getPost(id) {
        return await http.post("frontApi/post/getPost", id);
    },
    submitPost(post) {
        return http.post("frontApi/post/submitPost", post)
    },
    upLoad(file) {
        return http.post("frontApi/post/upLoad", file);
    },
    deleteImage(filePath) {
        return http.post("frontApi/post/deleteImage", filePath);
    },
    getPostItem(groupId) {
        return http.post("frontApi/post/getPostItem", groupId);

    },
    getPostByUserId(userId) {
        return http.post("frontApi/post/getPostByUserId", userId);
    }
}