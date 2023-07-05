declare module 'vue-layx'{
    interface Layx {
        msg(value:string,config:config)
    }
    interface config {
        dialogIcon:string
        position?:string
    }
    const layx:Layx
    export default layx
}