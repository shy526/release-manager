package com.github.qing.ssh;

import com.github.qing.ReleaseManagerShellTest;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SshManagerTest extends ReleaseManagerShellTest {
    @Autowired
    private SshManager sshManager;

    @Test
    public void getSession() {
        Session session = sshManager.getSession("192.168.3.200", "root", "root");
         sshManager.sftp(session, (channelSftp -> {

         }));
    }

    @Test
    public void exec() {
    }
}