<template>
  <HeaderView></HeaderView>
  <div class="login-content">
    <div class="login-flex">
      <div class="login-left">
        <p>万民用户知心托付&nbsp;&nbsp;&nbsp;&nbsp;<span>{{ platInfoStore.platInfo.historyAvgRate }}%</span>历史年化收益
        </p>
        <p>千万级技术研发投入&nbsp;&nbsp;&nbsp;&nbsp;亿级注册资本平台 </p>
      </div>
      <!---->
      <div class="login-box">
        <h3 class="login-title">用户注册</h3>
        <form action="javascript:void (0)" id="register_Submit">
          <div class="alert-input">
            <input type="text" class="form-border user-num my-div-input" name="mobile"
                   v-model="userRegister.email"
                   @blur="checkUserRegister(1)"
                   placeholder="请输入邮箱">
            <!--            <p class="prompt_num" v-show="typeNum == 1">{{ errMessage }}</p>-->
            <input type="password"
                   @blur="checkUserRegister(2)"
                   v-model="userRegister.password"
                   placeholder="请输入6-20位英文和数字混合密码" class="form-border user-pass my-div-input"
                   autocomplete name="password">
            <!--            <p class="prompt_pass" v-show="typeNum==2">{{ errMessage }}</p>-->
            <div class="form-yzm form-border my-div-input">
              <input class="yzm-write" type="text" v-model="userRegister.code" @blur="checkUserRegister(3)" name=""
                     placeholder="输入邮箱验证码">
              <input class="yzm-send"
                     type="button" :value="codeText" @click="requestEmailCode" id="yzmBtn"
              >
            </div>
            <!--            <p class="prompt_yan"></p>-->
          </div>
          <div class="alert-input-agree">
            <input type="checkbox" v-model="agree"/>我已阅读并同意<a href="javascript:;"
                                                                     target="_blank">《动力金融网注册服务协议》</a>
          </div>
          <div class="alert-input-btn">
            <input type="submit" @click="requestUserRegister" class="login-submit" value="注册">
          </div>
        </form>
        <div class="login-skip">
          已有账号？ <router-link to="/login">登录</router-link>
        </div>
      </div>

    </div>
  </div>

  <FooterView></FooterView>
</template>
<script setup lang="ts">
import {reactive, ref} from "vue";
import {PlatInfoStore} from "@/stores";
import layx from "vue-layx";
import HttpUtil from "@/api";
import md5 from 'js-md5'
import {useRouter} from "vue-router";

const router = useRouter()
const platInfoStore = PlatInfoStore()
const userRegister = reactive<{
  email: string,
  password: string,
  code: string
}>({
  code: "2222",
  email: "123456@163.com",
  password: "www.520.COM"
})
let codeText = ref<string>('获取验证码')
let isSend = ref<boolean>(false)
let agree = ref<boolean>(false)
const checkUserRegister = (type: number) => {
  switch (type) {
    case 1: {
      const exp = new RegExp("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
      let flag = true;
      if (!exp.test(userRegister.email)) {
        alert("邮箱格式不对，请输入正确的邮箱")
        return false
      }
      HttpUtil.get('/v1/user/email/exists', {
        email: userRegister.email
      }).then(value => {
        if (value.data.code != 1000) {
          flag = false
          alert(value.data.msg)
        }
      })
      return flag
    }
    case 2: {
      const exp = new RegExp("^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^\\da-zA-Z\\s]).{8,16}$")
      if (!exp.test(userRegister.password)) {
        alert('密码不符合要求，密码应在8-16位，包含字母大小写，数字，特殊字符')
        return false;
      }
      return true
    }
    case 3: {
      if (userRegister.code == '' || userRegister.code == undefined) {
        alert("验证码不能为空")
        return false;
      }
      if (userRegister.code.length != 4) {
        alert("验证码是4位码")
        return false;
      }
      return true
    }
  }
}
const requestEmailCode = () => {
  console.log(checkUserRegister(1))
  if (!checkUserRegister(1)) {
    return
  }
  if (!checkUserRegister(2)) {
    return;
  }
  // console.log(isSend.value)
  if (!isSend.value) {
    // 倒计时处理 默认60
    let second = 60
    const interval = setInterval(() => {
      isSend.value = true
      if (second <= 1) {
        codeText.value = '获取验证码'
        clearInterval(interval)
        isSend.value = false
      } else {
        second = second - 1
        codeText.value = second + "秒后重新获取"
      }
    }, 1000);
  }
  // HttpUtil.get('/v1/email/code/register', {
  //   email: userRegister.email
  // }).then(value => {
  //   if (value.data.code == 1000) {
  //     layx.msg('发送成功', {dialogIcon: 'info'})
  //   } else {
  //     alert(value.data.msg)
  //   }
  // })
}
const requestUserRegister = () => {
  if (agree.value) {
    console.log(checkUserRegister(1),1)
    console.log(checkUserRegister(2),2)
    console.log(checkUserRegister(3),3)
    // if (checkUserRegister(1)) {
    //   return;
    // }
    // if (checkUserRegister(2)) {
    //   return;
    // }
    // if (checkUserRegister(3)) {
    //   return;
    // }
    // userRegister.password = md5(userRegister.password)
    // HttpUtil.post('/v1/user/register',
    //     userRegister).then(value => {
    //   if (value.data.code == 1000) {
    //     router.push({
    //       path:'/login'
    //     })
    //   }
    // })
    // userRegister.password = 'www.520.COM'
  } else {
    alert('必须同意协议')
    return
  }

}
</script>
<style scoped>
.my-div-input {
  margin-bottom: 8px
}
</style>