import axios, {type AxiosResponse, type InternalAxiosRequestConfig} from "axios";


const requests = axios.create({
    baseURL: 'http://localhost:8000/api',
    timeout: 50000,
})

requests.interceptors.request.use((config: InternalAxiosRequestConfig<any>) => {
    // console.log(config)
    if (config.url == '/v1/user/realName'){
        let storageToken = localStorage.getItem("token")
        let userInfo = localStorage.getItem("userinfo")
        if (storageToken && userInfo){
            // 在header中传递userID和token
            config.headers['Authorization'] = storageToken
            config.headers['uid'] = JSON.parse(userInfo).uid
        }
    }
    console.log(config.headers)
    return config
},error => {
    return new Error(error)
})

requests.interceptors.response.use((res: AxiosResponse<any, any>) => {

    return res
}, error => {
    return Promise.reject(new Error(error))
})
export default requests