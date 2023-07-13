package com.xwh.front.interceptor;

import com.alibaba.fastjson.JSON;
import com.xwh.common.enums.ERRORCode;
import com.xwh.common.util.JwtUtil;
import com.xwh.front.view.R;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 作者:陈方银
 * 时间:2023/7/12
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String headerUid = request.getHeader("uid");
        boolean isRequestSend = false;
        // 获取token
        try {
            String authorization = request.getHeader("Authorization");
            if (StringUtils.isNotBlank(authorization)) {
                String jwt = authorization.substring(7);
                Claims claims = JwtUtil.readJwt(jwt);
                Integer jwtUid = claims.get("userId", Integer.class);
                if (headerUid.equals(String.valueOf(jwtUid))){
                    // token和发起请求是同一个人请求可以被处理
                    isRequestSend = true;
                }
            }
        }catch (Exception e){
            log.error("token校验错误:{}",e.getMessage());
        }
        // token 没有验证通过
        if (!isRequestSend){
            R r = R.error().setCode(ERRORCode.TOKEN_INVALID.getCode()).setMsg(ERRORCode.TOKEN_INVALID.getMessage());
            String errorJson = JSON.toJSONString(r);
            PrintWriter writer = response.getWriter();
            writer.print(errorJson);
            writer.flush();
            writer.close();
        }
        return isRequestSend;
    }


}
