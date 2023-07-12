import {defineStore} from "pinia";
import HttpUtil from "@/api";
import {PlatInfoType} from '@/interface/typeInterface'

export const PlatInfoStore = defineStore("platInfo", {
    state() {
        return {
            platInfo: {} as PlatInfoType
        }
    },
    actions: {
        async getPlatInfo() {
            await HttpUtil.get('/v1/plat/info').then(value => {
                if (value.data.code == 1000) {
                    this.platInfo = value.data.data
                }
            })
        }
    },
    getters: {}
})