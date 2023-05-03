package com.java3y.hades.example.apollo.demo;

import com.java3y.hades.core.HadesClient;
import groovy.lang.GroovyObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 3y
 * test
 */
@RestController
@Slf4j
public class SendController {

    @Autowired
    private HadesClient hadesClient;

    @RequestMapping("/test")
    private void test() {
        // 获取脚本对象，用接口接收
        SendSmsService sendSmsService = hadesClient.getInterfaceByName("com.java3y.hades.example.apollo.demo.TencentSmsService");
        sendSmsService.send();

        // 获取GroovyObject对象
        GroovyObject groovyObject = hadesClient.getGroovyObjectByName("com.java3y.hades.example.apollo.demo.TencentSmsService");
        groovyObject.invokeMethod("send", null);
        log.info("groovy object:{}", groovyObject);

        // 直接执行脚本对应的方法，得到返回值
        Object execute = hadesClient.execute("com.java3y.hades.example.apollo.demo.TencentSmsService", "send", null);
        System.out.println("result" + execute);
    }

}
