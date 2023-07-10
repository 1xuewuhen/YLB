package com.xwh.front.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwh.api.service.UserService;
import com.xwh.api.vo.RealNameVo;
import com.xwh.common.util.HttpUtils;
import com.xwh.front.config.AliyunRealName;
import com.xwh.front.service.AliyunRealNameService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈方银
 * @date 2023/7/6
 * @since 1.0
 */
@Service
public class AliyunRealNameServiceImpl implements AliyunRealNameService {
    @Resource
    private AliyunRealName aliyunRealName;

    @Override
    public boolean realName(RealNameVo realNameVo) {
        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + aliyunRealName.getAppcode());
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("idcard", realNameVo.getIdCard());
        querys.put("name", realNameVo.getName());
        String bodys = "";

        /**
         * 重要提示如下:
         * HttpUtils请从
         * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
         * 下载
         *
         * 相应的依赖请参照
         * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
         */
        //HttpResponse response = HttpUtils.doPost(aliyunRealName.getHost(), aliyunRealName.getPath(), method, headers, querys, bodys);
        //String string = EntityUtils.toString(response.getEntity());
        //获取response的body
        //System.out.println(EntityUtils.toString(response.getEntity()));

        String s = "{\"code\":\"10000\",\"message\":\"成功\",\"data\":{\"result\":\"1\"},\"seqNo\":\"4XU29Z4D1704061618\"}";
        JSONObject jsonObject = JSON.parseObject(s);
        return jsonObject.getInteger("code") == 10000;
    }
}
