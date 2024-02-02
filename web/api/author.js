import http from "@/utils/request";

export default {
    async bookAuthor(params) {
        return await http.post("frontApi/Author/bookAuthor", params);
    },
}