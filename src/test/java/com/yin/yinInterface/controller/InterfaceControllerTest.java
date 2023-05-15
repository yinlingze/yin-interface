package com.yin.yinInterface.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: yin7331
 * Date: 2023/4/23 0:37
 * Describe:
 */
@SpringBootTest
class InterfaceControllerTest {

    @Data
    static class Q{
        String qqq ;
    }
    @Test
    public void test1(){
        String url="http://localhost:8101/api/userInterfaceInfo/getTest";

        HashMap map = new HashMap();
        map.put("qqq", "qqqqqq");
        String response = HttpUtil.get(url,
                // 携带的参数，可以为null或空
                // 可以使用链式调用的方式添加更多参数
               map);
        System.out.println(response);
    }
    @Test
    public void test2(){
        HashMap<String, String> stringStringHashMap = new HashMap<String, String>();
        stringStringHashMap.put("qq", "qq1");
        stringStringHashMap.put("qq2", "qq21");
        stringStringHashMap.put("qq3", "qq31");
        String s = JSONUtil.toJsonStr(stringStringHashMap);
        System.out.println(s);
    }
}