package com.github.qing.ssh;

import com.github.qing.ReleaseManagerShellTest;
import com.jcraft.jsch.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SshManagerTest extends ReleaseManagerShellTest {
    @Autowired
    private SshManager sshManager;
    private Session session;

    @Test
    @Before
    public void getSession() {
        Session session = sshManager.getSession("192.168.3.200", "root", "root");
        Assert.assertTrue(session.isConnected());
        this.session = session;
    }

    @Test
    public void exec() {
    }
}