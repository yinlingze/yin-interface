package com.yin.yinInterface.controller;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yin.yinclientsdk.entities.User;
import org.apache.dubbo.config.annotation.Reference;
import org.example.server.GatewayRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Author: yin7331
 * Date: 2023/3/31 7:25
 * Describe:
 */

@RequestMapping("/user")
@RestController
public class YinInterfaceController {

    @Reference
    GatewayRequestService gatewayRequestService;

    @PostMapping("/name")
    public String postName(@RequestBody User user) {
        System.out.println(user.getUserName());
        return "postName : " + user.getUserName();
    }

    @PostMapping("/getData")
    public String postGetData(
            // 使用 @RequestHeader 注解获取请求消息头信息
            // name 或 value 属性：指定请求消息头名称
            // defaultValue 属性：设置默认值
            @RequestHeader(name = "accessKey", defaultValue = "missing") String accessKey,
            @RequestHeader(name = "apiSign", defaultValue = "missing") String apiSign,
            @RequestBody Map<String,Object> body
            ) {
        String bodyStrJson = JSONUtil.toJsonStr(body);

        Map<String, String> urlByApiSign = gatewayRequestService.getUrlByApiSign(accessKey, apiSign);
        String url = urlByApiSign.get("url");
        String method = urlByApiSign.get("method");

        if (method.equals("get")) {
            String s = HttpUtil.get(url, body);
            return s;

        }
        if (method.equals("post")) {

            String body1 = HttpRequest
                    .post(url)
                    .body(bodyStrJson)
                    .execute()
                    .body();
            return body1;
        }
        System.out.println(bodyStrJson);
        return "request faile" ;
    }
}
