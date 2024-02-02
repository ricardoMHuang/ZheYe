import http from "@/utils/request";

export default {
    async imageUrl(params) {
        return await http.post("frontApi/book/imageUrl", params);
    },
    async bookItem(params) {
        return await http.post("frontApi/book/bookItem", params);
    },
    async getBookBytype(params) {
        return await http.post("frontApi/book/bookByType", params);
    },
    async delete(params) {
        return await http.post("frontApi/book/delete", params);
    }
}