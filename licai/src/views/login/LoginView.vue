<template>
  <HeaderView></HeaderView>
  <div class="login-content">
    <div class="login-flex">
      <div class="login-left">
        <h3>加入动力金融网</h3>
        <p>坐享<span>{{ platInfoStore.platInfo.historyAvgRate }}%</span>历史年化收益</p>
        <p>平台用户<span>{{ platInfoStore.platInfo.registerUsers }}</span>位 </p>
        <p>累计成交金额<span>{{ platInfoStore.platInfo.sumBigMoney }}</span>元</p>
      </div>
      <!---->
      <div class="login-box">
        <h3 class="login-title">欢迎登录</h3>
        <form action="javascript:void (0)" id="login_Submit">
          <div class="alert-input">
            <!--<input class="form-border user-name" name="username" type="text" placeholder="您的姓名">
            <p class="prompt_name"></p>-->
            <input type="text" class="form-border user-num" v-model="userLogin.email" @blur="checkUserLogin(1)"
                   name="mobile" placeholder="请输入邮箱">
            <p class="prompt_num"></p>
            <input type="password" placeholder="请输入登录密码" v-model="userLogin.password" @blur="checkUserLogin(2)"
                   class="form-border user-pass"
                   name="password">
            <p class="prompt_pass"></p>
            <div class="form-yzm form-border">
              <input class="yzm-write" type="text" placeholder="输入邮箱验证码" @blur="checkUserLogin(3)"
                     v-model="userLogin.code">
              <input class="yzm-send" type="button" :value="codeText" @click="requestEmailCode" id="yzmBtn"
              >
            </div>
            <p class="prompt_yan"></p>
          </div>
          <div class="alert-input-btn">
            <input type="submit" class="login-submit" @click="requestUserLogin" value="登录">
          </div>
        </form>

      </div>

    </div>
  </div>
  <FooterView></FooterView>
</template>

<script setup lang="ts">
import {PlatInfoStore} from '@/stores'
import {reactive, ref} from "vue";
import HttpUtil from "@/api";
import layx from "vue-layx";
import md5 from "js-md5";
import {useRouter} from "vue-router";

const router = useRouter()
const platInfoStore = PlatInfoStore()
const userLogin = reactive<{
  email: string,
  password: string,
  code: string
}>({
  code: "2222",
  email: "1234567@163.com",
  password: "www.520.COM"
})
let codeText = ref<string>('获取验证码')
let isSend = ref<boolean>(false)
let sign = ref<boolean>(true)
let num = ref<number>(0)

//TODO 校验有问题需要修改一下
const checkUserLogin = async (type: number) => {
  if (num.value == 0) {
    switch (type) {
      case 1: {
        const exp = new RegExp("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        if (!exp.test(userLogin.email)) {
          alert("邮箱格式不对，请输入正确的邮箱")
          sign.value = false
          num.value++
          return
        }
        return
      }
      case 2: {
        const exp = new RegExp("^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^\\da-zA-Z\\s]).{8,16}$")
        if (!exp.test(userLogin.password)) {
          alert('密码不符合要求，密码应在8-16位，包含字母大小写，数字，特殊字符')
          sign.value = false
          num.value++
          return
        }
        return
      }
      case 3: {
        if (userLogin.code == '' || userLogin.code == undefined) {
          alert("验证码不能为空")
          sign.value = false
          num.value++
          return
        }
        if (userLogin.code.length != 4) {
          alert("验证码是4位码")
          sign.value = false
          num.value++
          return;
        }
        return
      }
    }
  }
  num.value = 0
}
const requestEmailCode = async () => {
  await checkUserLogin(1)
  await checkUserLogin(2)
  if (sign.value) {
    if (!isSend.value) {
      // 倒计时处理 默认60
      let second = 60
      const interval = setInterval(() => {
        isSend.value = true
        if (second <= 1) {
          codeText.value = '获取验证码'
          clearInterval(interval)
          isSend.value = false
          sign.value = true
        } else {
          second = second - 1
          codeText.value = second + "秒后重新获取"
        }
      }, 1000);
      await HttpUtil.get('/v1/email/code/login', {
        email: userLogin.email
      }).then(value => {
        if (value.data.code == 1000) {
          layx.msg('发送成功', {dialogIcon: 'info'})
        } else {
          alert(value.data.msg)
          codeText.value = '获取验证码'
          clearInterval(interval)
          isSend.value = false
          sign.value = true
        }
      })
    }
  }
}
const requestUserLogin = async () => {
  await checkUserLogin(1)
  await checkUserLogin(2)
  await checkUserLogin(3)
  if (sign.value) {
    userLogin.password = md5(userLogin.password)
    let userRegister = {...userLogin}
    await HttpUtil.post('/v1/user/email/login', userRegister).then(value => {
      if (value.data.code == 1000){
        // alert("登录成功")
        localStorage.setItem("token","Banner " + value.data.accessToken)
        localStorage.setItem("userinfo",JSON.stringify(value.data.data))

        // 登录之后，如果name没有值，需要进入到实名认证页面
        // 有值，进入用户中心
        if (value.data.data.name==''){
          router.push({
            path:'/realName',
          })
        }else {
          router.push({
            path:'/userCenter',
            query:{
              uid:value.data.data.uid
            }
          })
        }
      }else {
        alert(value.data.msg)
      }
    })
    userLogin.password = 'www.520.COM'
    sign.value = true
  }
}
</script>


<style scoped>

</style>