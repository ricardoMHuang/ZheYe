import http from "@/utils/request";

export default {
    async insertSupport(support) {
        return await http.post("frontApi/support/insertSupport", support);
    },
    async deleteSupport(support) {
        return await http.post("frontApi/support/deleteSupport", support);
    },
    async select(support) {
        return await http.post("frontApi/support/select", support);
    }
}