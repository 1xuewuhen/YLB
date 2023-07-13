<template>

  <HeaderView></HeaderView>

  <div class="login-content">
    <div class="login-flex">
      <div class="login-left"></div>
      <!---->
      <div class="login-box">
        <h3 class="login-title">实名认证</h3>
        <form action="javascript:void (0)" id="renZ_Submit">
          <div class="alert-input">
            <input type="text" class="form-border user-name" @blur="checkRealName(1)" v-model.trim="realNameVo.name"
                   name="username"
                   placeholder="请输入您的真实姓名">
            <p class="prompt_name"></p>
            <input type="text" class="form-border user-sfz" @blur="checkRealName(2)" v-model.trim="realNameVo.idCard"
                   name="sfz"
                   placeholder="请输入15位或18位身份证号">
            <p class="prompt_sfz"></p>
            <input type="text" class="form-border user-num" name="mobile" @blur="checkRealName(3)"
                   v-model.trim="realNameVo.email"
                   placeholder="请输入邮箱号">
            <p class="prompt_num"></p>
            <div class="form-yzm form-border">
              <input class="yzm-write" type="text" v-model.trim="realNameVo.code" placeholder="输入验证码">
              <input class="yzm-send" type="button" @click="getCode" :value="codeText">
            </div>
            <p class="prompt_yan"></p>
          </div>
          <div class="alert-input-agree">
            <input type="checkbox" v-model="sign" class="fa fa-square-o">我已阅读并同意
            <a href="javascript:void (0)">《动力金融网注册服务协议》</a>
          </div>
          <div class="alert-input-btn">
            <input type="submit" class="login-submit" @click="authentication" value="认证">
          </div>
        </form>
        <div class="login-skip">
          暂不认证？ <a href="javascript:;">跳过</a>
        </div>
      </div>

    </div>
  </div>

  <FooterView></FooterView>
</template>

<script setup lang="ts">
import {reactive, ref} from "vue";
import layx from "vue-layx";
import HttpUtil from "@/api";
import {useRouter} from "vue-router";

const router = useRouter()
const sign = ref(false)
const codeText = ref<string>('输入验证码')
const flag = ref<boolean>(false)
const realNameVo = reactive<{
  email?: string
  name?: string
  idCard?: string
  code?: string
}>({
  "code": "2222",
  "email": "1234567@163.com",
  "idCard": "123550032002136784",
  "name": "血无痕"
})

const authentication = () => {
  if (!sign.value) {
    layx.msg("请阅读协议", {dialogIcon: "warn"})
    return
  }
  checkRealName(1)
  checkRealName(2)
  checkRealName(3)
  HttpUtil.post("/v1/user/realName",
      realNameVo
  ).then(value => {
    if (value.data.code == 1000) {
      layx.msg("认证成功", {dialogIcon: 'info'})
      let userInfo = localStorage.getItem("userinfo");
      router.push({
        path: '/userCenter',
        query: {
          uid:JSON.parse(userInfo).uid
        }
      })
    } else {
      layx.msg('认证失败', {dialogIcon: 'warn'})
    }
  })
}
const getCode = async () => {
  checkRealName(3)
  checkRealName(2)
  checkRealName(1)
  if (!flag.value) {
    let text = '秒后获取验证码'
    let num = 60
    const interval = setInterval(() => {
      flag.value = true
      if (num <= 1) {
        codeText.value = '输入验证码'
        num = 60
        flag.value = false
        clearInterval(interval)
      } else {
        num = num - 1
        codeText.value = num + text
      }
    }, 1000);
    await HttpUtil.get('/v1/email/code/register', {
      email: realNameVo.email
    }).then(value => {
      if (value.data.code == 1000) {
        layx.msg('发送成功', {dialogIcon: 'info'})
      } else {
        layx.msg(value.data.msg, {dialogIcon: 'warn'})
      }
    })
  }

}
const checkRealName = (num: number) => {
  switch (num) {
    case 1: {
      if (realNameVo.name == null || realNameVo.name == '') {
        layx.msg("姓名不能为空", {
          dialogIcon: 'warn'
        })
        return
      }
      break
    }
    case 2: {
      const regExp = new RegExp("^\\d{8,18}|[0-9x]{8,18}|[0-9X]{8,18}?$")
      if (!regExp.test(realNameVo.idCard as string)) {
        layx.msg("身份证校验失败", {dialogIcon: 'warn'})
        return;
      }
      break
    }
    case 3: {
      const regExp = new RegExp("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
      if (!regExp.test(realNameVo.email as string)) {
        layx.msg("邮箱校验失败", {dialogIcon: 'warn'})
        return;
      }
      break
    }
  }
}

</script>

<style scoped>

</style>