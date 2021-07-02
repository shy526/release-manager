package com.github.qing.dubbo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ccxh.httpclient.common.HttpResult;
import top.ccxh.httpclient.service.HttpClientService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qing
 */
@Component
public class DubboManager {
    private static final String LOGIN_URL = "/api/dev/user/login?userName=%s&password=%s";
    private static final String SERVICES_BY_IP = "/api/dev/service?pattern=ip&filter=%s&page=0&size=%s";
    private static final String AUTHORIZATION = "Authorization";
    @Autowired
    private HttpClientService httpClientService;

    public String login(String host, String userName, String password) {
        HttpResult httpResult = httpClientService.get(host + String.format(LOGIN_URL, userName, password));
        return httpResult.getEntityStr();

    }

    public void selectServicesByIp(String host, String token, String ip) {
        Map<String, String> header = buildAuthorization(token);
        HttpResult httpResult = httpClientService.get(host + String.format(SERVICES_BY_IP, ip, 1), null, header);
        JSONObject result = JSON.parseObject(httpResult.getEntityStr());
        Integer totalElements = result.getInteger("totalElements");
        httpResult = httpClientService.get(host + String.format(SERVICES_BY_IP, ip, totalElements), null, header);
        result = JSON.parseObject(httpResult.getEntityStr());
        System.out.println("result = " + result);
    }

    private Map<String, String> buildAuthorization(String token) {
        Map<String, String> result = new HashMap<>();
        result.put(AUTHORIZATION, token);
        return result;
    }
}
