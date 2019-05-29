package ua.com.alparibank.network.domain;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alparibank.network.model.NetworkDevice;
import ua.com.alparibank.network.service.ConfigReceiverService;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigReciever {

    private static Logger logger = LoggerFactory.getLogger(ConfigReceiverService.class);

    public void save(NetworkDevice device, String copyCommand) {

        if (device.getHostname().contains("-fw-") && copyCommand.startsWith("copy start")) {
            copyCommand = copyCommand.replace("copy start ftp://", "copy start ftp://   ");
        }

        if (device.getHostname().contains("-fw-") && copyCommand.startsWith("copy running")) {
            copyCommand = copyCommand.replace("copy running ftp://", "copy /noconfirm running-config ftp://");
        }

        try {

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            // Create a JSch session to connect to the server
            Session session = jsch.getSession("ciscobackup", device.getIp(), 22);
            session.setPassword("password");
            session.setConfig(config);
            // Establish the connection
            logger.info("Establishing Connection...");
            session.connect();
            logger.info("Connection established.");

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(copyCommand);

            OutputStream out = channel.getOutputStream();
            channel.connect();

            out.write(("\n").getBytes());
            Thread.sleep(500);
            out.write(("\n").getBytes());
            Thread.sleep(500);
            if (device.getHostname().contains("-fw-")){
                out.write(("\n").getBytes());
                Thread.sleep(500);
                out.write(("\n").getBytes());
                Thread.sleep(500);
            }
            out.flush();
            channel.connect();
            InputStream in = channel.getInputStream();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    logger.info("Exit Status: "
                            + channel.getExitStatus());
                    break;
                }
                Thread.sleep(1000);
            }

            channel.disconnect();
            session.disconnect();
            logger.info("DONE!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
