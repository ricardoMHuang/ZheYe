import http from "@/utils/request";

export default {
    /*书架图书获取*/
    async bookCollection(params) {
        return await http.post("frontApi/bookCollect/bookCoItem", params);
    },
    /*删除收藏书籍*/
    async deleteBookCollection(params) {
        return await http.post("frontApi/bookCollect/deleteBookCollection", params);
    },
    /*加入书架*/
    async addBookCollection(params) {
        return await http.post("frontApi/bookCollect/addBookCollection", params);
    },
    /*查询书籍是否在用户书架*/
    async selectBookCollect(params) {
        return await http.post("frontApi/bookCollect/selectBookCollect", params);
    },
}