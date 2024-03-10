import http from "@/utils/request";

export default {
    async SearchList(params) {
        return await http.post("frontApi/list", params);
    },
    async getUserInfo(params) {
        return await http.post("/frontApi/user/info", params);
    },
    async getUserArticle(params) {
        return await http.post("/frontApi/user/article", params);
    },
    async registerEmail(params) {
        return await http.post1("frontApi/user/registerEmail", params);
    },
    async register(params) {
        return await http.post("frontApi/user/register", params);
    },
    async login(params) {
        return await http.post("frontApi/user/login", params);
    },

    /*获取回复人信息*/
    async getReplyer(id) {
        return await http.post("frontApi/user/getReplyer", id);
    },
    /*获取回复人信息*/
    async updateInfo(params) {
        return await http.post("frontApi/user/updateInfo", params);
    },

    async selectNickname(id) {
        return await http.post("frontApi/user/selectNickname", id);

    }
}