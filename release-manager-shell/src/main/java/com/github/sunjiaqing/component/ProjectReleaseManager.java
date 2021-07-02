package com.github.sunjiaqing.component;

import com.github.sunjiaqing.config.MidwayProvider;
import com.github.sunjiaqing.dubbo.DubboManager;
import com.github.sunjiaqing.dubbo.TestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author sjq
 */
@ShellComponent
@ShellCommandGroup("dubbo")
public class ProjectReleaseManager {
 

    @Autowired
    private MidwayProvider myInputProvider;
    @Autowired
    private DubboManager dubboManager;
    @Autowired
    private ApplicationContext applicationContext;
    @DubboReference
    private TestService testService;

    @ShellMethod("发布war")
    public String releaseWar(String args) {
        return "You said " + args;
    }

    @ShellMethod("发布Jar")
    public String releaseJar(String args) {
        return "You said "+testService.sayHello();
    }


}
