<template>

  <HeaderView></HeaderView>

  <div class="login-content">
    <div class="login-flex">
      <div class="login-left"></div>
      <!---->
      <div class="login-box">
        <h3 class="login-title">实名认证</h3>
        <form action="" id="renZ_Submit">
          <div class="alert-input">
            <input type="text" class="form-border user-name" name="username" placeholder="请输入您的真实姓名">
            <p class="prompt_name"></p>
            <input type="text" class="form-border user-sfz" name="sfz" placeholder="请输入15位或18位身份证号">
            <p class="prompt_sfz"></p>
            <input type="text" class="form-border user-num" name="mobile" placeholder="请输入11位手机号">
            <p class="prompt_num"></p>
            <div class="form-yzm form-border">
              <input class="yzm-write" type="text" placeholder="输入短信验证码">
              <input class="yzm-send" type="text" value="获取验证码" disabled="disabled" id="yzmBtn"
                     readonly="readonly">
            </div>
            <p class="prompt_yan"></p>
          </div>
          <div class="alert-input-agree">
            <i class="fa fa-square-o"></i>我已阅读并同意<a href="javascript:;"
                                                           target="_blank">《动力金融网注册服务协议》</a>
          </div>
          <div class="alert-input-btn">
            <input type="submit" class="login-submit" value="认证">
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

</script>

<style scoped>

</style>