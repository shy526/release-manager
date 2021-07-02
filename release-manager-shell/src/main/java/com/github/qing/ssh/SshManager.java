package com.github.qing.ssh;

import com.jcraft.jsch.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (JSchException e) {
            log.error(e.getMessage(), e);
        }
        return session;
    }

    public String exec(Session session, String command) {
        String result = null;
        Channel channel = null;
        try {
            channel = session.openChannel(CHANNEL_EXEC);
            ((ChannelExec) channel).setCommand(command);
            ((ChannelExec) channel).setErrStream(System.err);
            channel.connect();
            result = read(channel.getInputStream());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            channelDisconnect(channel);
            sessionDisconnect(session);
        }
        return result;
    }

    public void sftp(Session session, SftpHandle handle) {
        Channel channel = null;
        try {
            channel = session.openChannel("sftp");
            ChannelSftp sftp = (ChannelSftp) channel;
            handle.process(sftp);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            channelDisconnect(channel);
            sessionDisconnect(session);
        }
    }


    private void channelDisconnect(Channel channel) {
        if (channel != null) {
            channel.disconnect();
        }
    }

    private void sessionDisconnect(Session session) {
        if (session != null) {
            session.disconnect();
        }
    }

    private String read(InputStream inputStream) {
        StringBuilder result = new StringBuilder(16);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String buffer;
            while ((buffer = reader.readLine()) != null) {
                result.append("\n").append(buffer);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result.toString();
    }

    interface SftpHandle {
        void process(ChannelSftp channelSftp);
    }

}
