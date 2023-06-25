/// <reference types="vite/client" />

declare module "*.vue" {
    import {defineComponent} from "vue";
    const component = defineComponent<any, any, {}>()
    export default component
}