package com.github.qing.dubbo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=" + false})
class DubboManagerTest {
    @Autowired
    private DubboManager dubboManager;
    private String testHost = "http://localhost:8080";
    private String token;

    @Test
    @BeforeEach
    void login() {
        String login = dubboManager.login(testHost, "root", "root");
        this.token = token;
        assertNotNull("token is nul",login);
    }

    @Test
    void selectServicesByIp() {
        String ip = "192.168.3.5";
        dubboManager.selectServicesByIp(testHost, token, ip);

    }
}