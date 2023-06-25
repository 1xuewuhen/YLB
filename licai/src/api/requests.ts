import axios, {type AxiosResponse, type InternalAxiosRequestConfig} from "axios";


const requests = axios.create({
    baseURL: 'http://localhost:8000/api',
    timeout: 50000,
    headers: {

    }
})

requests.interceptors.request.use((config: InternalAxiosRequestConfig<any>) => {

    return config
})

requests.interceptors.response.use((res: AxiosResponse<any, any>) => {

    return res
}, error => {
    return Promise.reject(new Error(error))
})
export default requests