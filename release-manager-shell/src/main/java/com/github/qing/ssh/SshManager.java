package com.github.qing.ssh;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author qing
 */
@Component
@Slf4j
public class SshManager {
    private static final int SSH_PORT = 22;
    private static final String CHANNEL_EXEC = "exec";

    public Session getSession(String host, String userName, String password) {
        Session session = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(userName, host);
            session.setPassword(password);
            session.setUserInfo(null);
            session.connect();
        } catch (JSchException e) {
            log.error(e.getMessage(), e);
        }
        return session;
    }

    public String exec(Session session, String command) {
        Channel channel = null;
        try {
            channel = session.openChannel(CHANNEL_EXEC);
            ((ChannelExec) channel).setCommand(command);
            channel.connect();
        } catch (JSchException e) {
            log.error(e.getMessage(), e);
        } finally {
            channelDisconnect(channel);
            sessionDisconnect(session);
        }
        return null;
    }

    private void channelDisconnect(Channel channel) {
        if (channel != null && channel.isConnected()) {
            channel.disconnect();
        }
    }

    private void sessionDisconnect(Session session) {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }
}
