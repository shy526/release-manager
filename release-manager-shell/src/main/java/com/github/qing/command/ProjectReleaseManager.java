package com.github.qing.command;

import com.github.qing.config.MidwayProvider;
import com.github.qing.dubbo.DubboManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author qing
 */
@ShellComponent
@ShellCommandGroup("dubbo")
public class ProjectReleaseManager {
 

    @Autowired
    private MidwayProvider myInputProvider;
    @Autowired
    private DubboManager dubboManager;

    @ShellMethod("发布war")
    public String releaseWar(String args) {
        return "You said " + args;
    }

    @ShellMethod("发布Jar")
    public String releaseJar(String args) {
        return "You said "+args;
    }


}
