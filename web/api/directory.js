import http from "@/utils/request";

export default {
    async getDirectory(params) {
        return await http.post("frontApi/directory/directories", params);
    },
    async getBookContent(params) {
        return await http.post("frontApi/directory/bookContent", params);
    }
}