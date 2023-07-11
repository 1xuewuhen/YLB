import {fileURLToPath, URL} from 'node:url'

import {defineConfig, loadEnv} from 'vite'
import vue from '@vitejs/plugin-vue'
import {visualizer} from 'rollup-plugin-visualizer'
import {VitePWA} from "vite-plugin-pwa";

// TODO 部署到tomcat路由跳转有问题，建议之间使用nginx部署
const selectEnvType = (mode: string, env: Record<string, string>): string => {
    if (mode == 'development') {
        // console.log(env)
        return env.VITE_CONTEXT_PATH
    } else if (mode == 'production') {
        if (env.VITE_TYPE == 1) {
            return env.VITE_TOMCAT_CONTEXT_PATH
        } else {
            return env.VITE_NGINX_CONTEXT_PATH
        }
    }
}

// https://vitejs.dev/config/
export default ({mode}: any) => {
    const env = loadEnv(mode, process.cwd());
    return defineConfig({
        base: selectEnvType(mode, env),
        plugins: [
            vue(), visualizer({open: true}),
            VitePWA({
                workbox:{
                    cacheId:"wuHen",//缓存名称
                    runtimeCaching:[
                        {
                            urlPattern:/.*\.js.*/, //缓存文件
                            handler:"StaleWhileRevalidate", //重新验证时失效
                            options:{
                                cacheName:"wuHen-js", //缓存js，名称
                                expiration:{
                                    maxEntries:30, //缓存文件数量 LRU算法
                                    maxAgeSeconds:30 * 24 * 60 * 60 //缓存有效期

                                }
                            }
                        },
                        {
                            urlPattern:/.*\.css.*/, //缓存文件
                            handler:"StaleWhileRevalidate", //重新验证时失效
                            options:{
                                cacheName:"wuHen-css", //缓存js，名称
                                expiration:{
                                    maxEntries:30, //缓存文件数量 LRU算法
                                    maxAgeSeconds:30 * 24 * 60 * 60 //缓存有效期
                                }
                            }
                        }
                    ]
                },
            })

        ],
        resolve: {
            alias: {
                '@': fileURLToPath(new URL('./src', import.meta.url))
            }
        },
        build: {
            outDir: 'ylb',
            assetsDir: 'static',
            chunkSizeWarningLimit: 2000,
            cssCodeSplit: true,
            sourcemap: false,
            minify: 'esbuild',
            assetsInlineLimit: 4000
        },
    })

}