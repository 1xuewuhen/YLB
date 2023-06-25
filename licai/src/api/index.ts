import requests from "@/api/requests";

class HttpUtil {

    static async get(url: string, data?: {}) {
        return await requests({
            url: url,
            method: 'get',
            params: data
        })
    }

    static async post(url: string, data?: {}) {
        return await requests({
            url: url,
            method: 'post',
            data: data
        })
    }
}

export default HttpUtil