package com.github.qing.dubbo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = { InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=" + false })
class DubboManagerTest {
    @Autowired
    private DubboManager dubboManager;
    private String testHost = "http://localhost:8080";

    @Test
    void login() {
    }

    @Test
    void selectServicesByIp() {
        String ip="192.168.3.5";
        dubboManager.selectServicesByIp(testHost,dubboManager.login(testHost, "root", "root"),ip );
    }
}