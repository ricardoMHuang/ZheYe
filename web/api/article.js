import http from "@/utils/request";

export default {
    async getArticleItem(params) {
        return await http.post("frontApi/article/articleItem", params);
    },
    async getArticleInt(params) {
        return await http.post("frontApi/article/articlePlease", params);
    },
}