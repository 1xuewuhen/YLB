import axios, {type AxiosResponse, type InternalAxiosRequestConfig} from "axios";
import layx from "vue-layx";

const requests = axios.create({
    baseURL: 'http://localhost:8000/api',
    timeout: 50000,
})

const url = ["/v1/user/realName", "/v1/recharge/records","/v1/user/UserAllAccountInfo"]
requests.interceptors.request.use((config: InternalAxiosRequestConfig<any>) => {
    const configUrl = config.url
    // console.log(config.url?.lastIndexOf('?'));
    // console.log(config.url.substring(0,config.url?.lastIndexOf('?')))
    url.forEach(item => {

        if (configUrl.substring(0,configUrl.lastIndexOf('?')) == item) {
            // console.log(item)
            let storageToken = sessionStorage.getItem("token")
            let userInfo = sessionStorage.getItem("userinfo")
            if (storageToken && userInfo) {
                // 在header中传递userID和token
                config.headers['Authorization'] = storageToken
                config.headers['uid'] = JSON.parse(userInfo).uid
            }
        }
    })
    // console.log(config.headers)
    return config
}, error => {
    return new Error(error)
})

requests.interceptors.response.use((res: AxiosResponse<any, any>) => {
    if (res.data.code != 1000) {
        if (res.data.code == 13000) {
            layx.msg(res.data.msg, {dialogIcon: 'warn'})
            // window.location.href = '/login'
        } else {
            layx.msg(res.data.msg, {dialogIcon: 'warn'})
        }
    }
    return res
}, error => {
    // window.location.href = '/'
    return Promise.reject(new Error(error))
})
export default requests